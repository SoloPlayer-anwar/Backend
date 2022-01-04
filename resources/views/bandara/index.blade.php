<x-app-layout>
    <x-slot name="header">
        <h2 class="font-semibold text-xl text-gray-800 leading-tight">
            {{ __('Bandara') }}
        </h2>
    </x-slot>

    <div class="py-12">
        <div class="max-w-7xl mx-auto sm:px-6 lg:px-8">
            <div class="mb-10">
                <a href="{{ route('bandara.create') }}" class="bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded">
                    + Bandara
                </a>
            </div>
            <div class="bg-white">
                <table class="table-auto w-full">
                    <thead>
                    <tr>
                        <th class="border px-6 py-4">ID</th>
                        <th class="border px-6 py-4">Nama Bandara</th>
                        <th class="border px-6 py-4">Provinsi</th>
                        <th class="border px-6 py-4">Action</th>
                    </tr>
                    </thead>
                    <tbody>
                        @forelse($bandara as $item)
                            <tr>
                                <td class="border px-6 py-4">{{ $item->id }}</td>
                                <td class="border px-6 py-4 ">{{ $item->nama_bandara }}</td>
                                <td class="border px-6 py-4">{{$item->provinsi}}</td>
                                <td class="border px-6 py- text-center">
                                    <form action="{{ route('bandara.destroy', $item->id) }}" method="POST" class="inline-block">
                                        {!! method_field('delete') . csrf_field() !!}
                                        <button type="submit" class="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 mx-2 rounded inline-block">
                                            Delete
                                        </button>
                                    </form>
                                </td>
                            </tr>
                        @empty
                            <tr>
                               <td colspan="6" class="border text-center p-5">
                                   Data Tidak Ditemukan
                               </td>
                            </tr>
                        @endforelse
                    </tbody>
                </table>
            </div>
            <div class="text-center mt-5">
                {{ $bandara->links() }}
            </div>
        </div>
    </div>
</x-app-layout>
