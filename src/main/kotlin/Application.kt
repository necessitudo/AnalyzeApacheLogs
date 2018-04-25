import javafx.stage.Stage
import tornadofx.*

class Application: App(){
    override val primaryView = Parsing::class

    override fun start(stage: Stage) {
        importStylesheet("/style.css")
        stage.isResizable = false
        super.start(stage)
    }

}