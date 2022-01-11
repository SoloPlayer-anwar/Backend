<?php

use App\Http\Controllers\API\BandaraController;
use App\Http\Controllers\API\DestinasiController;
use App\Http\Controllers\API\ExploreController;
use App\Http\Controllers\API\MidtransController;
use App\Http\Controllers\API\ProductController;
use App\Http\Controllers\API\TransactionController;
use App\Http\Controllers\API\UserController;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

Route::middleware('auth:sanctum')->group(function(){
    Route::get('user', [UserController::class, 'fetch']);
    Route::post('user', [UserController::class, 'updateProfile']);
    Route::post('user/photo', [UserController::class,'uploadPhoto']);
    Route::post('logout', [UserController::class, 'logout']);


    Route::get('transaction', [TransactionController::class, 'transaction']);
    Route::post('transaction/{id}', [TransactionController::class, 'updateTransaction']);
    Route::post('checkout', [TransactionController::class, 'checkout']);
});

Route::post('login', [UserController::class, 'login']);
Route::post('register', [UserController::class, 'register']);
Route::get('product', [ProductController::class, 'product']);
Route::post('product/{id}', [ProductController::class, 'updateProduct']);
Route::get('bandara', [BandaraController::class, 'bandara']);
Route::get('destinasi', [DestinasiController::class, 'destinasi']);
Route::post('destinasi/{id}', [DestinasiController::class, 'updateDestinasi']);
Route::get('explore', [ExploreController::class, 'explore']);


Route::post('midtrans/callback', [MidtransController::class, 'midtrans']);
