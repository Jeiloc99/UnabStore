package me.jeisonsalcedo.unabstore.home


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ListenerRegistration
import me.jeisonsalcedo.unabstore.data.Producto
import me.jeisonsalcedo.unabstore.data.ProductoRepository

class HomeViewModel : ViewModel() {

    private val repo = ProductoRepository()
    private var listener: ListenerRegistration? = null

    var productos: List<Producto> by mutableStateOf(emptyList())
        private set

    var mensaje: String? by mutableStateOf(null)
        private set

    fun escucharTiempoReal() {
        listener?.remove()
        listener = repo.escuchar(
            onChange = { productos = it },
            onError  = { mensaje = it.localizedMessage }
        )
    }

    fun agregar(nombre: String, descripcion: String, precio: Double) {
        if (nombre.isBlank()) { mensaje = "El nombre es obligatorio"; return }
        if (precio < 0) { mensaje = "El precio no puede ser negativo"; return }
        repo.agregar(Producto(nombre = nombre, descripcion = descripcion, precio = precio)) { ok, _ ->
            mensaje = if (ok) "Producto agregado" else "Error al agregar"
        }
    }

    fun eliminar(id: String) {
        repo.eliminar(id) { ok ->
            mensaje = if (ok) "Producto eliminado" else "Error al eliminar"
        }
    }

    override fun onCleared() {
        listener?.remove()
        super.onCleared()
    }
}
