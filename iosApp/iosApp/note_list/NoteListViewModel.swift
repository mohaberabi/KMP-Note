//
//  NoteListViewModel.swift
//  iosApp
//
//  Created by mohab erabi on 18/05/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation

import shared


extension NoteListScreen {
    
 @MainActor  class NoteListViewModel : ObservableObject {
        
     private var noteDataSource:NoteDataSource?=nil
     
     
     private let searchNotes = SearchNotesUseCase()
     private var notes = [NoteModel]()
    
    @Published private(set) var filteredNotes = [NoteModel]()
     @Published var searchText = "" {
         didSet {
             self.filteredNotes = searchNotes.execute(notes: self.notes, query: searchText)
         }
     }
     
     
     @Published private(set) var isSearching = false
     
     
     init(noteDataSource:NoteDataSource?=nil) {
         self.noteDataSource=noteDataSource
     }
     
     
     
     func  loadNotes() {
                        
         noteDataSource?.getAllNotes(completionHandler: {
            
             notes , error in self.notes = notes ?? []
             
             self.filteredNotes=self.notes
             
         })
         
  
     }
     
     
     
     func deleteNodeById(id:Int64?){
         if id != nil {
             noteDataSource?.deleteNoteBuyId(id: id!, completionHandler: {
                 
                 error in
                 self.loadNotes()
         
             })
         }
     }
     
     
     func toggleSearching(){
         self.isSearching = !self.isSearching
         if !self.isSearching {searchText = " "}
     }
     
     
     
     func setNoteDataSource(noteDataSource:NoteDataSource){
         self.noteDataSource=noteDataSource
     
     }
     
     
     
    }
}
