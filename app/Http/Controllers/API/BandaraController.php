<?php

namespace App\Http\Controllers\API;

use App\Helpers\ResponseFormmater;
use App\Http\Controllers\Controller;
use App\Models\Bandara;
use Illuminate\Http\Request;

class BandaraController extends Controller
{
    public function bandara(Request $request)
    {
        $id = $request->input('id');


        if($id)
        {
            $bandara = Bandara::find($id);

            if($bandara)
            {
                return ResponseFormmater::success(
                    $bandara,
                    'Data bandara berhasil diambil'
                );
            }

            else {
                return ResponseFormmater::error(
                    null,
                    'Data bandara kosong',
                    404
                );
            }
        }

        $bandara = Bandara::query();

        return ResponseFormmater::success(
            $bandara->get(),
            'Data bandara berhasil diambil'
        );
    }
}
