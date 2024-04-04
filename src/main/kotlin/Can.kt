import javafx.scene.canvas.Canvas


class Can(val gv : GraphView, x:Double, y:Double) : Canvas(x,y) {
    private fun draw() {
    }

    override fun isResizable() : Boolean {
        return true;
    }

    override fun prefWidth(height: Double): Double {
        return getWidth()
    }

    override fun prefHeight(width: Double): Double {
        return getHeight()
    }

    init {
        // Redraw canvas when canvas is resized
        widthProperty().addListener { evt ->  gv.displayGraph() }
        heightProperty().addListener { evt -> gv.displayGraph() }
    }
}
