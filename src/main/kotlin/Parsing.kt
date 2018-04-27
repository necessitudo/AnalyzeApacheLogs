import javafx.beans.Observable
import javafx.beans.property.ReadOnlyObjectWrapper
import javafx.beans.value.ObservableValue
import javafx.scene.control.*
import tornadofx.*
import javafx.scene.layout.AnchorPane
import javafx.stage.FileChooser
import javafx.util.Callback
import javafx.beans.property.SimpleStringProperty



class Parsing : View() {
    override val root: AnchorPane by fxml()

    lateinit var path : TextField
    lateinit var chooseFileBtn : Button
    lateinit var runBtn : Button
    lateinit var treeData: TreeTableView<LineTree>
    lateinit var branchColumn: TreeTableColumn<LineTree,String>
    lateinit var amountColumn: TreeTableColumn<LineTree,String>


    init {
        title = "Parsing Apache logs"

        chooseFileBtn.action {

            val fileChooser = FileChooser();
            val file = fileChooser.showOpenDialog(null)

            path.text = file?.absolutePath

        }

        runBtn.action {

            clearAllBranchsTree()
            fillTree()
        }


    }

    private fun fillTree() {

        var line1 = LineTree("22 mart", "19")
        var rootNode = TreeItem(line1)

        var line2 = LineTree("23 mart", "22")
        var line2Node = TreeItem(line2)

        rootNode.children.add(line2Node)

        branchColumn.setCellValueFactory({ cellData -> SimpleStringProperty(cellData.getValue().getValue().branch) })
        amountColumn.setCellValueFactory({ cellData -> SimpleStringProperty(cellData.getValue().getValue().amount) })


        treeData.root = rootNode


    }

    private fun clearAllBranchsTree() {
       // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}