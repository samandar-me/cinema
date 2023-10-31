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
 
    func makeUIViewController(context: Context) -> some UIViewController {
        return MainKt.MainViewController()
    }
    func updateUIViewController(_ uiViewController: UIViewControllerType, context: Context) {
        
    }
}
