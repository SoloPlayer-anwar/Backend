<?php

namespace App\Http\Controllers;

use App\Http\Requests\BandaraRequest;
use App\Models\Bandara;
use Illuminate\Http\Request;

class BandaraController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $bandara = Bandara::paginate(10);

        return view('bandara.index', [
            'bandara' => $bandara
        ]);
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        return view('bandara.create');
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(BandaraRequest $request)
    {
        $data = $request->all();
        $data['picture_pesawat'] = $request->file('picture_pesawat')->store('assets/pesawat', 'public');

        Bandara::create($data);
        return redirect()->route('bandara.index');
    }
    /**
     * Display the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
        //
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function edit($id)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, $id)
    {
        //
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function destroy(Bandara $bandara)
    {
        $bandara->delete();
        return redirect()->route('bandara.index');
    }
}
