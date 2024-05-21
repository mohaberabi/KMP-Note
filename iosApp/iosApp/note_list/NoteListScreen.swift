//
//  NoteListScreen.swift
//  iosApp
//
//  Created by mohab erabi on 18/05/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

import shared
struct NoteListScreen: View {
    private var noteDataSource:NoteDataSource
  @StateObject  var viewModel = NoteListViewModel(noteDataSource: nil)
    
    
    @State private var isNoteSelected  = false
    @State private var selectedNote :Int64? = nil
    
    init(noteDataSource:NoteDataSource){
        self.noteDataSource=noteDataSource
    }
    
    
    
    var body: some View {

        VStack {
            ZStack {
                       NavigationLink(
                        
                        destination:
                                        
                                        NoteDetailScreen(noteDataSource: self.noteDataSource, noteId: selectedNote),
                                      
                                      isActive: $isNoteSelected
                       ) {
                           EmptyView()
                       }.hidden()
                       NoteTextField<NoteDetailScreen>(onSearchToggled: {
                           viewModel.toggleSearching()
                       }, destinationProvider: {
                           NoteDetailScreen(
                               noteDataSource: noteDataSource,
                               noteId: selectedNote
                           )
                       }, isSearchActive: viewModel.isSearching, searchText: $viewModel.searchText)
                       .frame(maxWidth: .infinity, minHeight: 40)
                       .padding()
                       
                       if !viewModel.isSearching {
                           Text("All notes")
                               .font(.title2)
                       }
                   }
            
            List {
                ForEach(viewModel.filteredNotes,id: \.self.id){
                    note in Button(action: {
                        isNoteSelected = true
                        selectedNote = note.id?.int64Value
                    }
                    ){
                        
                        NoteItem(note:note, onDelete: {viewModel.deleteNodeById(id: note.id?.int64Value)})
                    }
                }
            }.onAppear(){
                viewModel.loadNotes()
            }.listStyle(.plain)
                .listRowSeparator(.hidden)
        }.onAppear{
            viewModel.setNoteDataSource(noteDataSource: noteDataSource)
        }
    }
}

