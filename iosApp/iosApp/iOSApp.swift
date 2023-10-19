import SwiftUI
import shared

@main
struct iOSApp: App {
	var body: some Scene {
		WindowGroup {
            GeometryReader { geo in
                ComposeView(
                    topSafeArea: Float(geo.safeAreaInsets.top), bottomSafeArea: Float(geo.safeAreaInsets.bottom))
                .ignoresSafeArea()
                .onTapGesture {
                        UIApplication.shared.sendAction(#selector(UIResponder.resignFirstResponder), to: nil, from: nil, for: nil)
                    }
            }
		}
	}
}
