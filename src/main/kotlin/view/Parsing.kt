package view

import common.getRootNode
import data.LineTree
import javafx.beans.property.SimpleStringProperty
import javafx.scene.control.Button
import javafx.scene.control.TextField
import javafx.scene.control.TreeTableColumn
import javafx.scene.control.TreeTableView
import javafx.scene.layout.AnchorPane
import javafx.stage.FileChooser
import tornadofx.*

class Parsing : View() {
    override val root: AnchorPane by fxml()

    lateinit var path : TextField
    lateinit var chooseFileBtn : Button
    lateinit var runBtn : Button
    lateinit var treeView: TreeTableView<LineTree>
    lateinit var branchColumn: TreeTableColumn<LineTree,String>
    lateinit var amountColumn: TreeTableColumn<LineTree,String>

    init
    {
        title = "Parsing Apache logs"

        chooseFileBtn.action {

            val fileChooser = FileChooser();
            val file = fileChooser.showOpenDialog(null)

            path.text = file?.absolutePath

        }

        runBtn.action {

            fillTree()
        }

        //test
        path.text = "/Users/olegdubrovin/Yandex.Disk.localized/Загрузки/access.log"
        fillTree()


    }

    fun fillTree() {

        branchColumn.setCellValueFactory({ cellData -> SimpleStringProperty(cellData.getValue().getValue().branch) })
        amountColumn.setCellValueFactory({ cellData -> SimpleStringProperty(cellData.getValue().getValue().amount.toString()) })

        treeView.root = getRootNode(path.text)


    }

}