<?php

namespace App\Http\Controllers\API;

use App\Helpers\ResponseFormmater;
use App\Http\Controllers\Controller;
use App\Models\Explore;
use Illuminate\Http\Request;

class ExploreController extends Controller
{
    public function explore(Request $request)
    {
        $id = $request->input('id');


        if($id)
        {
            $explore = Explore::find($id);

            if($explore)
            {
                return ResponseFormmater::success(
                    $explore,
                    'Data Explore berhasil di ambil'
                );
            }

            else {
                return ResponseFormmater::error(
                    null,
                    'Data Explore tidak ada gaes',
                    404
                );
            }
        }

        $explore = Explore::query();

        return ResponseFormmater::success(
            $explore->get(),
            'Data list explore berhasil diambil'
        );
    }
}
