//
//  DamagingComponent.swift
//  TWDFS
//
//  Created by Ars Poyezzhayev on 13.11.16.
//  Copyright © 2016 AlexeyMukhin. All rights reserved.
//

import Foundation
import SpriteKit
import GameplayKit

class DamagingComponent: GKComponent {
    
    let damage: Int
    let destroySelf: Bool
    var lastDamageTime: TimeInterval
    weak var target: GKEntity?
    let aoe: Bool
    let entityManager: EntitiesManager
    
    func makeDamage(){
        target.component.takeDamage(damage: damage)
    }
    
    override func update(deltaTime seconds: TimeInterval) {
        super.update(deltaTime: seconds)
        
        // Get required components
        guard let enemyNode = target.component.node else { return }
        let node = self.entity.component.node

    }
}
