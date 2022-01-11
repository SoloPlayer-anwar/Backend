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
            $destinasi->where('category', 'like', '%' .$category, '%');
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
