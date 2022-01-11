<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateTransactionsTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('transactions', function (Blueprint $table) {
            $table->id();


            $table->integer('user_id');
            $table->integer('product_id')->nullable();
            $table->integer('destinasi_id')->nullable();
            $table->integer('quantity');
            $table->integer('total');
            $table->string('status');
            $table->text('payment_url');

            $table->string('checkin')->nullable();
            $table->string('nama_bandara')->nullable();
            $table->string('provinsi')->nullable();
            $table->string('jam_terbang')->nullable();
            $table->text('picture_pesawat')->nullable();

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
        Schema::dropIfExists('transactions');
    }
}
