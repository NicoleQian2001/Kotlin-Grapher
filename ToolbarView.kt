import javafx.collections.FXCollections.observableArrayList
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.geometry.Orientation
import javafx.geometry.Pos
import javafx.scene.control.*
import javafx.scene.layout.*

class ToolbarView (
    private val model: Model,
    private val controller: Main
    ) : HBox(), IView {
        val choiceBox = ChoiceBox<String>()
        override fun updateView() {
            choiceBox.items = observableArrayList(model.dataSets.keys)
            choiceBox.selectionModel.select(model.curDatasetNum) // set default value to increasing
        }

        init {
            prefHeight(30.0)

            val datasetLabel = Label("Dataset:")

            choiceBox.items = observableArrayList(model.dataSets.keys)
            choiceBox.selectionModel.select(model.curDatasetNum) // set default value to increasing
            val sep = Separator(Orientation.VERTICAL)

            val newBtn = Button("New")
            newBtn.prefWidth = 80.0

            val spinner = Spinner<Int>(0,100,1)
            newBtn.onAction =
                EventHandler {  event: ActionEvent ->
                    model.createNewXDataset(spinner.value) // pass val of spinner to createNewXDataset function
                }
            choiceBox.onAction =
                EventHandler {
                    if (choiceBox.selectionModel.selectedIndex >= 0) {
                        model.changeCurDatasetNum(choiceBox.selectionModel.selectedIndex)
                    }
            }

            alignment = Pos.CENTER_LEFT
            padding = Insets(10.0)
            spacing = 10.0
            children.addAll(datasetLabel,choiceBox,sep,newBtn,spinner)

            model.addView(this)
        }
    }
