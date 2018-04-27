import javafx.beans.property.SimpleStringProperty
import kotlin.reflect.KMutableProperty
import kotlin.reflect.KMutableProperty1

data class LineTree(val branch:String, val amount:String) {

    fun branchProperty():KMutableProperty1<LineTree, String> {
        return branchProperty()
    }


}