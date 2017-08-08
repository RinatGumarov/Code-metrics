import SpriteKit
import GameplayKit

enum AnimationState: String {
    case Idle
    case Walk
    case Hit
    case Dead
    case Attacking
}

struct Animation {
    let animationState: AnimationState
    let textures: SKTexture
}

class AnimationComponent: GKComponent {
    
    let node: SKSpriteNode

    var animations: Animation
    
    var requestedAnimationState: AnimationState?

    
    override func update(deltaTime: TimeInterval) {
        super.update(deltaTime: deltaTime)
        
        if let animationState = requestedAnimationState {
            runAnimationForAnimationState(animationState: animationState)
        }
    }

}


