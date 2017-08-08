//
//  GameScene.swift
//  RussiansVsZombies
//
//  Created by Dmitriy Kapitun on 15/02/17.
//  Copyright © 2017 Dmitriy Kapitun. All rights reserved.
//

import SpriteKit
import GameplayKit

class GameScene: SKScene {

    var lastUpdateTime: TimeInterval = 0

    var gameManager: GameManager!
    var levelManager: LevelManager!

    var gameCamera = SKCameraNode()

    // hud
    var hud = SKNode()
    var playButton = SKNode()
    var pauseButton = SKNode()
    var goldLabel: SKLabelNode!
    var livesLabel: SKLabelNode!
    var waveLabel: SKLabelNode!
    var winLabel: SKLabelNode!


    override func didMove(to view: SKView) {
        super.didMove(to: view)

        //gameManager = GameManager.sharedInstance

        //gameCamera = childNode(withName: "MainCamera") as! SKCameraNode
        //camera = gameCamera

        //hud = gameCamera.childNode(withName: "Hud") as! SKNode!
        //playButton = hud.childNode(withName: "StartGame") as! SKNode!
        //pauseButton = hud.childNode(withName: "PauseGame") as! SKNode!
        //goldLabel = hud.childNode(withName: "GoldLabel") as! SKLabelNode!
        //livesLabel = hud.childNode(withName: "LivesLabel") as! SKLabelNode!
        //waveLabel = hud.childNode(withName: "WaveLabel") as! SKLabelNode!
        //winLabel = hud.childNode(withName: "WinLabel") as! SKLabelNode!


        //playButton.isHidden = false
        //pauseButton.isHidden = true

        //levelManager = LevelManager(viewportISO: childNode(withName: "ISOMap") as! Viewport, gameCamera: gameCamera)

        levelManager.entitiesManager.loadTowerSelectorNodes()

        gameManager.set(scene: self)

        //gameManager.stateMachine.enter(ReadyState.self)

    }




    override func didFinishUpdate() {

        levelManager.didFinishUpdate()
        //goldLabel.text = "Gold: \( gameManager.gold)"
        //livesLabel.text = "Lives: \( gameManager.lives)"
        //waveLabel.text = "Wave: \( levelManager.waveManager.currentWave)"
    }

    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {

        for touch in touches {
            let touchLocation = touch.location(in: self)
            let touchedNode = self.atPoint(touchLocation)

            if currentState {
                gameManager.stateMachine.enter(ActiveState)

            } else {
                levelManager.touchesBegan(with: event)
            }
        }
    }


    // Camera Movement
    override func touchesMoved(_ touches: Set<UITouch>, with event: UIEvent?) {
        levelManager!.touchesMoved(touches, with: event)
    }
}