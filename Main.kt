import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.control.ScrollPane
import javafx.scene.layout.*
import javafx.stage.Stage

class Main : Application() {

    val model = Model()

    // need a ref to this to show/hide the add/edit dialog
    val dialogOverlay = BorderPane()

    override fun start(stage: Stage) {
        // window name
        stage.title = "A2 Grapher (j55qian)"

        // a StackPane is the root so we can place the add/edit overlap on
        // top of the main interface
        val root = StackPane()

        // this is the main interface
        val main = BorderPane()
        // the interface in the middle of the main interface
        val mid = BorderPane()
        mid.top = TitlepaneView(model,this)

        val scrollPane = ScrollPane()
        scrollPane.content = DataTableView(model)
        scrollPane.isFitToWidth = true
        // only show vertical scroll bar when necessary
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        mid.left = scrollPane

        mid.center = GraphView(model, this)
        mid.right = DataStatView(model,this)

        // top toolbar
        main.top = ToolbarView(model, this)
        main.center = mid

        // bottom status bar
        main.bottom = StatusbarView(model)

        // add the main interface and overlay to the root StackPanel
        root.children.addAll(main)

        // Add grid to a scene (and the scene to the stage)
        val scene = Scene(root, 800.0, 600.0)
        stage.scene = scene
        stage.minWidth = 600.0
        stage.minHeight = 400.0

        stage.show()

    }
}
