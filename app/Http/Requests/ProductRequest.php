<?php

namespace App\Http\Requests;

use Illuminate\Foundation\Http\FormRequest;

class ProductRequest extends FormRequest
{
    /**
     * Determine if the user is authorized to make this request.
     *
     * @return bool
     */
    public function authorize()
    {
        return true;
    }

    /**
     * Get the validation rules that apply to the request.
     *
     * @return array
     */
    public function rules()
    {
        return [
            'name' => 'required|max:255',
            'rate' => 'required',
            'picture_path' => 'sometimes|required|image|mimes:png,jpg,jpeg|max:5048',
            'category' => '',
            'lat' => 'required',
            'long' => 'required',
            'map' => 'required',
            'place' => 'required',
            'price' => 'required|integer',
            'expired' => 'required',
            'description' => 'required',
            'quantity' => '',
            'total' => '',
            'checkin' => ''

        ];
    }
}
