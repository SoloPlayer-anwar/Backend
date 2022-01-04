<?php

namespace App\Http\Controllers\API;

use App\Actions\Fortify\PasswordValidationRules;
use App\Helpers\ResponseFormmater;
use App\Http\Controllers\Controller;
use App\Models\User;
use Exception;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\Hash;
use Illuminate\Support\Facades\Validator;

class UserController extends Controller
{

    use PasswordValidationRules;

    public function login(Request $request)
    {
        try {
            $request->validate([
                'email' => 'email|required',
                'password' => 'required'
            ]);

            $credentials = request(['email', 'password']);

            if(!Auth::attempt($credentials))
            {
                return ResponseFormmater::error([
                    'message' => 'Unauthorized'
                ], 'Authentication Failed', 404);
            }

            $user = User::where('email', $request->email)->first();
            if(!Hash::check($request->password, $user->password, []))
            {
                throw new \Exception('invalid credentials');
            }

            $tokenResult = $user->createToken('authToken')->plainTextToken;
            return ResponseFormmater::success([
                'access_token' => $tokenResult,
                'token_type' => 'Bearer',
                'user' => $user
            ], 'Authenticated');
        }
        catch(Exception $error)
        {
            return ResponseFormmater::error([
                'message' => 'ada yang salah pada coding',
                'error' => $error
            ], 'Authentication Failed', 404);
        }
    }

    public function register(Request $request)
    {
        try {
            $request->validate([
                'name' => ['required', 'string', 'max:255'],
                'email' => ['required','string', 'email', 'max:255','unique:users'],
                'password' => $this->passwordRules()
            ]);

            User::create([
                'name' => $request->name,
                'email' => $request->email,
                'address' => $request->address,
                'phone' => $request->phone,
                'city' => $request->city,

                'password' => Hash::make($request->password)
            ]);

            $user = User::where('email', $request->email)->first();

            $tokenResult = $user->createToken('authToken')->plainTextToken;

            return ResponseFormmater::success([
                'access_token' => $tokenResult,
                'token_type' => 'Bearer',
                'user' => $user
            ], 'Register Success');
        }
        catch(Exception $error)
        {
            return ResponseFormmater::error([
                'message' => 'Ada yang salah pada coding',
                'error' => $error
            ], 'Authentication Failed', 404);
        }
    }

    public function fetch(Request $request)
    {
        return ResponseFormmater::success($request->user(), 'Data profile berhasil diambil');
    }

    public function logout(Request $request)
    {
        $token =  $request->user()->currentAccessToken()->delete();
        return ResponseFormmater::success($token, 'Token dihancurkan');
    }

    public function updateProfile(Request $request)
    {
        $data = $request->all();
        $user = Auth::user();

        $user->update($data);

        return ResponseFormmater::success($user, 'Profile berhasil di update');
    }

    public function uploadPhoto(Request $request)
    {
        $validator = Validator::make($request->all(), [
            'file' => 'required|image|max:2048',
        ]);

        if($validator->fails())
        {
            return ResponseFormmater::error(['error' => $validator->errors()], 'Update Photo gagal', 404);
        }

        if($request->file('file'))
        {
            $file = $request->file->store('assets/user', 'public');

            $user = Auth::user();
            $user->profile_photo_path = $file;
            $user->update();

            return ResponseFormmater::success(
                ['file'],
                'File berhasil diupload'
            );
        }
    }
}
