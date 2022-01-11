<?php

namespace App\Http\Requests;

use Illuminate\Foundation\Http\FormRequest;

class ExploreRequest extends FormRequest
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
            'name_guide' => 'required|max:255',
            'photo_guide' => 'sometimes|required|image|mimes:png,jpg,jpeg|max:5048',
            'judul_guide' => 'required',
            'address_destination' => 'required',
            'video' => 'required',
            'photo_destination' => 'sometimes|required|image|mimes:png,jpg,jpeg|max:5048'
        ];
    }
}
