<?php

namespace App\Http\Controllers\API;

use App\Helpers\ResponseFormmater;
use App\Http\Controllers\Controller;
use App\Models\Destinasi;
use App\Models\Product;
use App\Models\Transaction;
use Exception;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;
use Midtrans\Config;
use Midtrans\Snap;
use phpDocumentor\Reflection\Types\Null_;
use phpDocumentor\Reflection\Types\Nullable;

use function PHPUnit\Framework\equalToWithDelta;

class TransactionController extends Controller
{
    public function transaction(Request $request)
    {
        $id = $request->input('id');
        $limit = $request->input('limit', 12);
        $product_id = $request->input('product_id');
        $destinasi_id = $request->input('destinasi_id');
        $status = $request->input('status');


        if($id)
        {
            $transaction = Transaction::with(['product','destinasi','user'])->find($id);

            if($transaction)
            {
                return ResponseFormmater::success(
                    $transaction,
                    'Data transaction berhasil diambil'
                );
            }

            else {
                return ResponseFormmater::error(
                    null,
                    'Data transaction tidak ada',
                    404
                );
            }
        }

        $transaction = Transaction::with(['product','destinasi', 'user'])
                        ->where('user_id', Auth::user()->id);

        if($product_id)
        {
            $transaction->where('product_id', $product_id);
        }

        if($destinasi_id)
        {
            $transaction->where('destinasi_id', $destinasi_id);
        }

        if($status)
        {
            $transaction->where('status', $status);
        }

        return ResponseFormmater::success(
            $transaction->paginate($limit),
            'Data list Transaction berhasil diambil'
        );
    }

    public function updateTransaction (Request $request, $id)
    {
        $transaction = Transaction::findOrFail($id);

        $transaction->update($request->all());

        return ResponseFormmater::success($transaction, 'Data transaction berhasil di update');
    }

    public function checkout(Request $request)
    {

        $request->validate([
            'user_id' => 'required|exists:users,id',
            'product_id' => 'exists:products,id',
            'destinasi_id' => 'exists:destinasis,id',
            'quantity' => 'required',
            'total' => 'required',
            'status' => 'required',
            'checkin' => 'required'
        ]);

        $transaction = Transaction::create([
            'user_id' => $request->user_id,
            'product_id' => $request->product_id,
            'destinasi_id' => $request->destinasi_id,
            'quantity' => $request->quantity,
            'total' => $request->total,
            'status' => $request->status,
            'payment_url' => '',
            'checkin' => $request->checkin,
            'nama_bandara' => '',
            'provinsi' => '',
            'nama_pesawat' => '',
            'jam_terbang' => ''
        ]);


        Config::$serverKey = config('services.midtrans.serverKey');
        Config::$isProduction = config('services.midtrans.isProduction');
        Config::$isSanitized = config('services.midtrans.isSanitized');
        Config::$is3ds = config('services.midtrans.is3ds');



        $transaction = Transaction::with(['product','destinasi', 'user'])->find($transaction->id);

        $midtrans = [
            'transaction_details' => [
                'order_id' => $transaction->id,
                'gross_amount' => (int)  $transaction->total,
            ],

            'customer_details' => [
                'first_name' => $transaction->user->name,
                'email' => $transaction->user->email
            ],
            'enabled_payment' => [
                'gopay',
                'bank_transfer',
                'shoopepay',
                'indomaret',
                'alfamart',
            ],
            'vtweb' => []
        ];

        try {
            $paymentUrl = Snap::createTransaction($midtrans)->redirect_url;
            $transaction->payment_url = $paymentUrl;
            $transaction->save();

            return ResponseFormmater::success($transaction, 'Transaction Berhasil');
        }
        catch(Exception $error)
        {
            return ResponseFormmater::error($error->getMessage(), 'Transaction gagal');
        }
    }
}
