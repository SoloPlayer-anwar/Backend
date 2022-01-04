<?php

namespace App\Models;

use Carbon\Carbon;
use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Support\Facades\Storage;

class Bandara extends Model
{
    use HasFactory;
/**
     * The attributes that are mass assignable.
     *
     * @var string[]
     */
   protected $fillable = [
       'nama_bandara',
       'provinsi',
       'picture_pesawat',
       'jam_terbang'
   ];

   public function getCreatedAtAttribute($value)
   {
       return Carbon::parse($value)->timestamp;
   }

   public function getUpdatedAtAttribute($value)
   {
       return Carbon::parse($value)->timestamp;
   }

   public function getPicturePesawatAttribute()
    {
        return url('') . Storage::url($this->attributes['picture_pesawat']);
    }
}
