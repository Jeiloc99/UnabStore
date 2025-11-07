package me.jeisonsalcedo.unabstore.data


import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.Query

class ProductoRepository {

    private val col = FirebaseFirestore.getInstance().collection("productos")

    fun agregar(producto: Producto, onResult: (Boolean, String?) -> Unit) {
        col.add(producto)
            .addOnSuccessListener { onResult(true, it.id) }
            .addOnFailureListener { onResult(false, it.localizedMessage) }
    }

    fun eliminar(id: String, onResult: (Boolean) -> Unit) {
        col.document(id).delete()
            .addOnSuccessListener { onResult(true) }
            .addOnFailureListener { onResult(false) }
    }

    fun escuchar(
        onChange: (List<Producto>) -> Unit,
        onError: (Exception) -> Unit
    ): ListenerRegistration {
        return col.orderBy("nombre", Query.Direction.ASCENDING)
            .addSnapshotListener { snaps, e ->
                if (e != null) { onError(e); return@addSnapshotListener }
                val list = snaps?.documents?.mapNotNull { d ->
                    d.toObject(Producto::class.java)?.copy(id = d.id)
                } ?: emptyList()
                onChange(list)
            }
    }
}
