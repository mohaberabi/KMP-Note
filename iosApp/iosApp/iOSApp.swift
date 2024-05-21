import SwiftUI
import shared
@main
struct iOSApp: App {
    
    
    private let databaseModule = DataBaseModule()
    
	var body: some Scene {
		WindowGroup {
            
            
            NoteListScreen(noteDataSource: databaseModule.noteDataSource)
			ContentView()
		}
	}
}
