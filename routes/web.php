<?php

use App\Http\Controllers\API\MidtransController;
use App\Http\Controllers\BandaraController;
use App\Http\Controllers\DestinasiController;
use App\Http\Controllers\ExploreController;
use App\Http\Controllers\ProductController;
use App\Http\Controllers\TransactionController;
use App\Http\Controllers\UserController;
use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

Route::get('/', function () {
    return view('welcome');
});

Route::middleware(['auth:sanctum', 'admin'])->get('/dashboard', function () {
    return view('dashboard');
})->name('dashboard');


Route::resource('users', UserController::class);
Route::resource('product', ProductController::class);
Route::get('transaction/{id}/status/{status}', [TransactionController::class, 'changeStatus'])->name('transaction.changeStatus');
Route::resource('transaction',TransactionController::class);
Route::resource('bandara',BandaraController::class);
Route::resource('destinasi', DestinasiController::class);
Route::resource('explore', ExploreController::class);


Route::get('midtrans/success', [MidtransController::class, 'success']);
Route::get('midtrans/unfinish', [MidtransController::class, 'unfinish']);
Route::get('midtrans/error', [MidtransController::class, 'error']);
