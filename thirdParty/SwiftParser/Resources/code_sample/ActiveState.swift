import SpriteKit
import GameplayKit

class ActiveState: State {
    override func didEnter(from previousState: GKState?) {
        let scene.playButton.isHidden = true
        let scene.pauseButton.isHidden = false
        let scene.levelManager.viewportISO.isPaused = false
        if currentWave {
            scene.levelManager.waveManager.startNextWave()
        }
    }
}
