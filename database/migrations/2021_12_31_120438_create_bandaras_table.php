<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateBandarasTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('bandaras', function (Blueprint $table) {
            $table->id();

            $table->string('nama_bandara')->nullable();
            $table->string('provinsi')->nullable();
            $table->text('picture_pesawat')->nullable();
            $table->string('jam_terbang')->nullable();

            $table->timestamps();


        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('bandaras');
    }
}
