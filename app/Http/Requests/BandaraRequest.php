<?php

namespace App\Http\Requests;

use Illuminate\Foundation\Http\FormRequest;

class BandaraRequest extends FormRequest
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
            'nama_bandara' => 'required',
            'provinsi' => 'required',
            'picture_pesawat' => 'sometimes|required|image|mimes:png,jpg,jpeg|max:5048',
            'jam_terbang' => 'required'
        ];
    }
}
