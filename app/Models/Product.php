<?php

namespace App\Models;

use Carbon\Carbon;
use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Support\Facades\Storage;

class Product extends Model
{
    use HasFactory;

     /**
     * The attributes that are mass assignable.
     *
     * @var string[]
     */
    protected $fillable = [
        'picture_path',
        'name',
        'rate',
        'category',
        'lat',
        'long',
        'map',
        'place',
        'price',
        'expired',
        'description',
        'quantity',
        'total',
        'checkin'

    ];

    public function getCreatedAtAttribute($value)
    {
        return Carbon::parse($value)->timestamp;
    }

    public function getUpdatedAtAttribute($value)
    {
        return Carbon::parse($value)->timestamp;
    }

    public function getPicturePathAttribute()
    {
        return url('') . Storage::url($this->attributes['picture_path']);
    }
}
