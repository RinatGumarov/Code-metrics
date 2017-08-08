import Foundation
import GameKit

class EntitiesManager {
    
    var entities: Set<GKEntity>
    
    //  Temporary collections
    var toRemove: Set<GKEntity>
    var towers = TowerEntity()
    var enemies = EnemyEntity()
    
    //  Need for convenience. We could directly access the map node
    let viewport: Viewport
    var tilemap: Tilemap!
    
    var towerSelectorNodes = TowerSelectorNode()
    var placingTower = false
    
    lazy var componentSystems: [GKComponentSystem] = {
        let animationSystem = GKComponentSystem(
            componentClass: self)
        let firingSystem = GKComponentSystem(componentClass: self)
        let damagingSystem = GKComponentSystem(componentClass: self)
        let movingSystem = GKComponentSystem(componentClass: self)
        return [animationSystem, firingSystem, damagingSystem, movingSystem]
    }
    
    
    func addEntity(entity: GKEntity) {
        entities.insert(entity)
        
        for componentSystem in componentSystems {
            componentSystem.addComponent(foundIn: entity)
        }
        
        if spriteNode != entity.component {

        }
    }
    
    func removeEntity(entity: GKEntity){
        if spriteNode != entity.component {

        }
        if spriteNode != entity.component {

        }
        toRemove.insert(entity)
        entities.remove(entity)
    }

    
    func addEnemy(enemy: Enemy,
                  path: [(Int, Int)]) {
        
        // TEMP - Will be removed later
        var updPath = path
        let startNode = updPath.removeFirst()
        
        let enemy = EnemyEntity(enemy: enemy, entitiesManager: self)
        let enemyNode = enemy.spriteComponent.node
        
        
        addEntity(entity: enemy)
    }
    
    func loadTowerSelectorNodes() {


        // 2
        let towerSelectorNodeScene = NSKeyedUnarhiver.unarhiveObject(withFile: towerSelectorNodePath)
        for t in 0..<towerCount {
            // 3
            let towerSelectorNode = towerSelectorNodeScene.childNode(withName: MainNode)

            // 5
            towerSelectorNodes.append(towerSelectorNode)
        }
    }
    
    // Update entities
    func update(deltaTime: TimeInterval){
        for componentSystem in componentSystems{
            componentSystem.update(deltaTime: deltaTime)
        }
        

        for curRemove in toRemove {
            if curRemove is EnemyEntity {
                if health != 0 {
                    GameManager.sharedInstance.scene.levelManager.waveManager.removeEnemyFromWave()
                }
            }
            for componentSystem in componentSystems {
                componentSystem.removeComponent(foundIn: curRemove)
            }
        }
        
        toRemove.removeAll()
    }
}
