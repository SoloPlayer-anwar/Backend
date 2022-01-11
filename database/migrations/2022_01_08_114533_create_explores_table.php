<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateExploresTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('explores', function (Blueprint $table) {
            $table->id();
            $table->timestamps();
            $table->string('name_guide')->nullable();
            $table->text('photo_guide')->nullable();
            $table->string('judul_guide')->nullable();
            $table->text('address_destination')->nullable();
            $table->string('video')->nullable();
            $table->text('photo_destination')->nullable();
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('explores');
    }
}
