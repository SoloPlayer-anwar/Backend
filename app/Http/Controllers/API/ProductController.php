<?php

namespace App\Http\Controllers\API;

use App\Helpers\ResponseFormmater;
use App\Http\Controllers\Controller;
use App\Models\Product;
use Illuminate\Http\Request;

class ProductController extends Controller
{
    public function product(Request $request)
    {
        $id = $request->input('id');
        $limit = $request->input('limit', 36);
        $name = $request->input('name');
        $category = $request->input('category');

        if($id)
        {
            $product = Product::find($id);

            if($product)
            {
                return ResponseFormmater::success(
                    $product,
                    'Data product berhasil diambil',
                );
            }

            else {
                return ResponseFormmater::error(
                    null,
                    'Data product tidak ada',
                    404
                );
            }
        }

        $product = Product::query();

        if($name)
        {
            $product->where('name', 'like', '%' .$name, '%');
        }

        if($category)
        {
            $product->where('category', 'like', '%' .$category, '%');
        }

        return ResponseFormmater::success(
            $product->paginate($limit),
            'Data list product berhasil diambil'
        );
    }

    public function updateProduct(Request $request, $id)
    {
        $product = Product::findOrFail($id);

        $request->validate([
            'quantity' => 'required',
            'total' => 'required',
            'checkin' => 'required',
            'nama_bandara' => 'required',
            'provinsi' => 'required',
            'jam_terbang' => 'required',
            'picture_pesawat' => 'required'
        ]);

        $product->update($request->all());

        return ResponseFormmater::success($product, 'Data product berhasil masuk');
    }
}
