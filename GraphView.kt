import javafx.geometry.Insets
import javafx.geometry.VPos
import javafx.scene.Group
import javafx.scene.control.Label
import javafx.scene.layout.*
import javafx.scene.paint.Color
import javafx.scene.text.TextAlignment

class GraphView (
    private val model: Model,
    private val controller: Main
) : Pane(), IView {
    override fun updateView() {
        displayGraph()
    }

    // init all labels and the canvas
    var canvas = Can(this,523.0,467.0)

    fun displayGraph() {
        // add widgets to canvas
        val gc = canvas.graphicsContext2D
        gc.clearRect(0.0,0.0,canvas.width,canvas.height)
        gc.stroke = Color.LIGHTGRAY
        gc.strokeRect(50.0, 50.0, canvas.width - 100.0, canvas.height - 100.0)
        gc.lineWidth = 1.0
        gc.stroke = Color.BLACK
        gc.strokeLine(50.0,canvas.height - 50.0,canvas.width - 50.0, canvas.height - 50.0) // x-Axis
        gc.stroke = Color.BLACK
        gc.strokeLine(50.0,50.0,50.0,canvas.height - 50.0) // y-Axis
        gc.textAlign = TextAlignment.CENTER
        gc.textBaseline = VPos.CENTER
        gc.fill = Color.BLACK

        // max height of bars
        val max = model.dataSets[model.curDatasetName]!!.data.maxByOrNull { it.toDouble() }
        // label above yAxis
        gc.fillText(max!!.toString(), 30.0,50.0)
        // label at origin of graph
        gc.fillText("0", 38.0,canvas.height - 50.0)
        // graph title
        gc.fillText(model.dataSets[model.curDatasetName]!!.title,canvas.width/2, 25.0)
        // xAxis label
        gc.fillText(model.dataSets[model.curDatasetName]!!.xAxis,canvas.width/2, canvas.height - 25.0)
        // yAxis label
        gc.fillText(model.dataSets[model.curDatasetName]!!.yAxis,25.0,canvas.height/2)

        val barWidth = calBarWidth(canvas.width - 100.0)

        // draw bars
        for (i in 1..model.dataSets[model.curDatasetName]!!.data.size) {
            gc.fill = Color.hsb((i * 36).toDouble(), 1.0, 1.0)
            gc.fillRect(50.0 + 5.0 * i + barWidth * (i - 1),canvas.height - 50.0 - model.dataSets[model.curDatasetName]!!.data.elementAt(i - 1).toDouble() / max!! * (canvas.height - 100.0), barWidth, model.dataSets[model.curDatasetName]!!.data.elementAt(i - 1).toDouble()/ max!! * (canvas.height - 100.0))
        }
    }

    //return width of each bar
    fun calBarWidth(w:Double) : Double {
        val dataset = model.dataSets[model.curDatasetName]
        println("${dataset!!.data!!.size}")
        val barWidth = (w - (dataset!!.data.size + 1) * 5.0) / dataset!!.data.size
        return barWidth
    }
    init {
        background = Background(BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY),)

        // the root node that holds the objects that we want to display on the stage
        val root = Group()

        root.children.add(canvas)
        children.add(root)

        canvas.widthProperty().bind(widthProperty())
        canvas.heightProperty().bind(heightProperty())

        model.addView(this)
    }

}
