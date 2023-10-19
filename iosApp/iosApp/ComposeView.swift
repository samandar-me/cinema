//
//  ComposeView.swift
//  iosApp
//
//  Created by Samandar Asiydinov on 19/10/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import shared
import SwiftUI

struct ComposeView: UIViewControllerRepresentable {
    
    private let topSafeArea: Float
    private let bottomSafeArea: Float
    
    init(topSafeArea: Float, bottomSafeArea: Float) {
        self.topSafeArea = topSafeArea
        self.bottomSafeArea = bottomSafeArea
    }
 
    func makeUIViewController(context: Context) -> some UIViewController {
        return MainKt.MainViewController(
            topSafeArea: topSafeArea,
            bottomSafeArea: bottomSafeArea
        )
    }
    func updateUIViewController(_ uiViewController: UIViewControllerType, context: Context) {
        
    }
}
