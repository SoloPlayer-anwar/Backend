<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateProductsTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('products', function (Blueprint $table) {
            $table->id();


            $table->text('picture_path')->nullable();
            $table->string('name')->nullable();
            $table->double('rate')->nullable();
            $table->string('category')->nullable();
            $table->double('lat')->nullable();
            $table->double('long')->nullable();
            $table->text('map')->nullable();
            $table->string('place')->nullable();
            $table->integer('price')->nullable();
            $table->string('expired')->nullable();
            $table->text('description')->nullable();

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
        Schema::dropIfExists('products');
    }
}
