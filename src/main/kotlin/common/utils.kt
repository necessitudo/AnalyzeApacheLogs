package common

import data.LineTree
import javafx.beans.property.SimpleStringProperty
import javafx.scene.control.TreeItem
import jdk.nashorn.internal.runtime.PropertyMap.newMap
import tornadofx.*
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths


fun getRootNode(pathToFile:String):TreeItem<LineTree>{

    val lineRoot =  LineTree("Overal", null)
    var rootNode = TreeItem(lineRoot)

    val treeData = HashMap<String?, HashMap<String?, HashMap<String?, ArrayList<String?>>>>()

    Files.lines(Paths.get(pathToFile), StandardCharsets.UTF_8).forEach({

        val wordDate = Regex(Patterns.DATE.expression).find(it)

        recoverMainBranch(wordDate, treeData)
        if (wordDate != null) {

            val year = Regex(Patterns.YEAR.expression).find(wordDate.value)?.value
            val month = Regex(Patterns.MONTH.expression).find(wordDate.value)?.value
            val day = Regex(Patterns.DAY.expression).find(wordDate.value)?.value

            if (!treeData.containsKey(year)) treeData.put(year, HashMap<String?, HashMap<String?, ArrayList<String?>>>())
            val lineYear = treeData.get(year)

            if  (lineYear != null && !lineYear.containsKey(month))  lineYear.put(month, HashMap<String?, ArrayList<String?>>())
            val lineMonth  = lineYear?.get(month)

            if (lineMonth != null && lineMonth.containsKey(day)) lineMonth.put(day, ArrayList<String?>())
            val lineDay = lineMonth?.get(day)


            println(year)

        }

    })

    return rootNode




}

fun recoverMainBranch(wordDate:MatchResult?, treeData:HashMap<String?, HashMap<String?, HashMap<String?, ArrayList<String?>>>>) {




}

