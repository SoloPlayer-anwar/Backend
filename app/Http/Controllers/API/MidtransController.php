<?php

namespace App\Http\Controllers\API;

use App\Http\Controllers\Controller;
use App\Models\Transaction;
use Illuminate\Http\Request;
use Midtrans\Config;
use Midtrans\Notification;

class MidtransController extends Controller
{
    public function midtrans(Request $request)
    {
        Config::$serverKey = config('services.midtrans.serverKey');
        Config::$isProduction = config('services.midtrans.isProduction');
        Config::$isSanitized = config('services.midtrans.isSanitized');
        Config::$is3ds = config('services.midtrans.is3ds');

        $notification = new Notification();

        $status = $notification->transaction_status;
        $type = $notification->payment_type;
        $fraud = $notification->fraud_status;
        $order_id = $notification->order_id;

        $transaction = Transaction::findOrFail($order_id);

        if($status == 'capture')
        {
            if($type == 'credit_card')
            {
                if($fraud == 'challenge')
                {
                    $transaction->status = 'PENDING';
                }
                else {
                    $transaction->status = 'SUCCESS';
                }
            }
        }

        else if($status == 'settlement') {
            $transaction->status = 'SUCCESS';
        }

        else if($status == 'pending') {
            $transaction->status = 'PENDING';
        }

        else if($status == 'deny') {
            $transaction->status = 'CANCEL';
        }

        else if($status ==  'expire') {
            $transaction->status = 'CANCEL';
        }

        else if($status == 'cancel'){
            $transaction->status = 'CANCEL';
        }

        $transaction->save();
    }

    public function success(Request $request)
    {
        return view('midtrans.success');
    }

    public function unfinish(Request $request)
    {
        return view('midtrans.unfinish');
    }


    public function error(Request $request)
    {
        return view('midtrans.error');
    }
}
