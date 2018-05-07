package common

import data.LineTree
import javafx.beans.property.SimpleStringProperty
import javafx.scene.control.TreeItem
import jdk.nashorn.internal.runtime.PropertyMap.newMap
import sun.rmi.runtime.Log
import tornadofx.*
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths


fun getRootNode(pathToFile:String):TreeItem<LineTree>{

    val lineRoot =  LineTree("Overal", null)
    var rootNode = TreeItem(lineRoot)

    val treeData = HashMap<String?, HashMap<String?, HashMap<String?, Any>>>()

    Files.lines(Paths.get(pathToFile), StandardCharsets.UTF_8).forEach({

        try {
            recoverMainBranch(it, treeData)
        } catch ( e:Exception) {
            println(it)
        }


    })

    return rootNode


}

fun recoverMainBranch(contentStr: String?, treeData: HashMap<String?, HashMap<String?, HashMap<String?, Any>>>) {

    val wordDate = Regex(Patterns.DATE.expression).find(contentStr!!)

    if (wordDate != null) {

        val year = Regex(Patterns.YEAR.expression).find(wordDate.value)?.value
        val month = Regex(Patterns.MONTH.expression).find(wordDate.value)?.value
        val day = Regex(Patterns.DAY.expression).find(wordDate.value)?.value

        if (!treeData.containsKey(year)) treeData.put(year, HashMap<String?, HashMap<String?,Any>>())
        val lineYear = treeData.get(year)

        if (lineYear != null && !lineYear.containsKey(month)) lineYear.put(month, HashMap<String?, Any>())
        val lineMonth = lineYear?.get(month)

        if (lineMonth != null && !lineMonth.containsKey(day)) lineMonth.put(day, HashMap<String?, Any>())
        val lineDay = lineMonth?.get(day)

        val collectionContent = contentStr.split("/")

        if (collectionContent.size>3) {

            var previousBranch = HashMap<String?, Any>()


            for (n in 2..collectionContent.size - 2) {

                if (n == 3) {

                    val lineData = lineDay as HashMap<String?, Any>
                    lineData.put(collectionContent.get(n), previousBranch)


                } else {

                    val newBranch = HashMap<String?, Any>()

                    previousBranch.put(collectionContent.get(n), newBranch)
                    previousBranch = newBranch

                }


            }
        }

    }

}

