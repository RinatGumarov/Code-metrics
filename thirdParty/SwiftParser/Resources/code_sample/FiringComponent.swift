
import SpriteKit
import GameplayKit

class FiringComponent: GKComponent {
    
    let tower: Tower
    let parentNode: SKNode
    weak var currentTarget: EnemyEntity?
    var timeTillNextShot: TimeInterval = 0
    //  we need entities handler, because firing component creates projectiles
    var entitiesManager: EntitiesManager
    

    override func update(deltaTime seconds: TimeInterval) {
        super.update(deltaTime: seconds)
        

        if timeTillNextShot > 0 { return }


        
        let projectile = ProjectileEntity(tower: tower, target: target, entitiesManager: entitiesManager)
        let projectileNode = projectile.spriteComponent.node

        entitiesManager.addEntity(entity: projectile)
        
    }

}


