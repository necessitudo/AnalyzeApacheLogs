import javafx.fxml.FXML
import tornadofx.*
import javafx.scene.layout.AnchorPane
import javafx.scene.control.Label

class Parsing : View() {
    override val root: AnchorPane by fxml()

    @FXML
    lateinit var path : Label

    init {
        title = "Parsing Apache logs"
    }


}
