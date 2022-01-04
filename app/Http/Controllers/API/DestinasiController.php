<?php

namespace App\Http\Controllers\API;

use App\Helpers\ResponseFormmater;
use App\Http\Controllers\Controller;
use App\Models\Destinasi;
use Illuminate\Http\Request;

class DestinasiController extends Controller
{
    public function destinasi(Request $request)
    {
        $id = $request->input('id');
        $limit = $request->input('limit', 36);
        $name = $request->input('name');
        $category = $request->input('category');
        $quantity = $request->input('quantity');
        $total = $request->input('total');
        $checkin = $request->input('checkin');
        $nama_bandara = $request->input('nama_bandara');
        $provinsi = $request->input('provinsi');


        if($id)
        {
            $destinasi = Destinasi::find($id);

            if($destinasi)
            {
                return ResponseFormmater::success(
                    $destinasi,
                    'Data destinasi berhasil diambil'
                );
            }

            else {
                return ResponseFormmater::error(
                    null,
                    'Data destinasi tidak ada',
                    404
                );
            }
        }

        $destinasi = Destinasi::query();

        if($name)
        {
            $destinasi->where('name', 'like', '%' .$name, '%');
        }

        if($category)
        {
            $destinasi->where('quantity', 'like', '%' .$quantity, '%');
        }

        if($total)
        {
            $destinasi->where('total', 'like', '%' .$total, '%');
        }

        if($checkin)
        {
            $destinasi->where('checkin', 'like', '%' .$checkin, '%');
        }


        if($nama_bandara)
        {
            $destinasi->where('nama_bandara', 'like', '%' .$nama_bandara, '%');
        }



        if($provinsi)
        {
            $destinasi->where('provinsi', 'like', '%' .$provinsi, '%');
        }

        return ResponseFormmater::success(
            $destinasi->paginate($limit),
            'Data list Destinasi berhasil diambil'
        );

    }

    public function updateDestinasi(Request $request, $id)
    {
        $destinasi = Destinasi::findOrFail($id);


        $request->validate([
            'quantity' => 'required',
            'total' => 'required',
            'checkin' => 'required',
            'nama_bandara' => 'required',
            'provinsi' => 'required',
            'picture_pesawat' => 'required'
        ]);

        $destinasi->update($request->all());

        return ResponseFormmater::success($destinasi, 'Data transaction berhasil di update');
    }
}
