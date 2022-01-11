<?php

namespace App\Models;

use Carbon\Carbon;
use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Support\Facades\Storage;

class Explore extends Model
{
    use HasFactory;

     /**
     * The attributes that are mass assignable.
     *
     * @var string[]
     */
    protected $fillable = [

        'name_guide',
        'photo_guide',
        'judul_guide',
        'address_destination',
        'video',
        'photo_destination'
    ];

    public function getCreatedAtAttribute($value)
    {
        return Carbon::parse($value)->timestamp;
    }

    public function getUpdatedAtAttribute($value)
    {
        return Carbon::parse($value)->timestamp;
    }

    public function getPhotoGuideAttribute()
    {
        return url('') . Storage::url($this->attributes['photo_guide']);
    }

    public function getPhotoDestinationAttribute()
    {
        return url('') . Storage::url($this->attributes['photo_destination']);
    }
}
