import javafx.fxml.FXML
import javafx.scene.control.Button
import tornadofx.*
import javafx.scene.layout.AnchorPane
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.control.TreeTableView
import javafx.stage.FileChooser

class Parsing : View() {
    override val root: AnchorPane by fxml()

    lateinit var path : TextField
    lateinit var chooseFileBtn : Button
    lateinit var treeData: TreeTableView<String>

    init {
        title = "Parsing Apache logs"

        val button = Button("Add")


        chooseFileBtn.action {

            val fileChooser = FileChooser();
            val file = fileChooser.showOpenDialog(null)

            path.text = file?.absolutePath

        }

    }


}
