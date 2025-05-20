package util;

import bd.*;
import modelo.*;
import bd.UsuarioDAO;

import org.w3c.dom.*;

import javax.swing.JOptionPane;
import javax.xml.XMLConstants;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.*;

import java.io.File;
import java.io.FileInputStream;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class ExportadorXML {

    private static final String RUTA_XML = "recursos/config/voidwear.xml";
    private static final String RUTA_XSD = "recursos/config/voidwear.xsd";

    public static void exportar(Usuario gestor) {
        try {
            // Crear documento XML
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            Element root = doc.createElement("voidwear");
            root.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
            root.setAttribute("xsi:noNamespaceSchemaLocation", "voidwear.xsd");
            doc.appendChild(root);


            // Productos
            Element productos = doc.createElement("productos");
            for (Producto p : ProductoDAO.obtenerTodos()) {
                Element producto = doc.createElement("producto");
                producto.setAttribute("id", String.valueOf(p.getIdProducto()));

                producto.appendChild(crearElemento(doc, "nombre", p.getNombre()));
                producto.appendChild(crearElemento(doc, "precio", String.valueOf(p.getPrecio())));
                producto.appendChild(crearElemento(doc, "categoria", p.getCategoria()));
                producto.appendChild(crearElemento(doc, "talla", p.getTalla()));
                producto.appendChild(crearElemento(doc, "color", p.getColor()));
                producto.appendChild(crearElemento(doc, "stock", String.valueOf(p.getStock())));

                productos.appendChild(producto);
            }
            root.appendChild(productos);

            // Usuarios
            Element usuarios = doc.createElement("usuarios");
            for (Usuario u : UsuarioDAO.obtenerTodos()) {
                Element usuario = doc.createElement("usuario");
                usuario.setAttribute("correo", u.getCorreo());

                usuario.appendChild(crearElemento(doc, "nombre", u.getNombre()));
                usuario.appendChild(crearElemento(doc, "rol", u.getRol().toString()));

                usuarios.appendChild(usuario);
            }
            root.appendChild(usuarios);

            // Clientes
            Element clientes = doc.createElement("clientes");
            for (Cliente c : ClienteDAO.obtenerTodos()) {
                Element cliente = doc.createElement("cliente");
                cliente.setAttribute("id", String.valueOf(c.getIdCliente()));

                cliente.appendChild(crearElemento(doc, "direccion", c.getDireccion()));
                cliente.appendChild(crearElemento(doc, "telefono", c.getTelefono()));
                cliente.appendChild(crearElemento(doc, "id_usuario", String.valueOf(c.getIdUsuario())));

                clientes.appendChild(cliente);
            }
            root.appendChild(clientes);

            // Pedidos
            Element pedidos = doc.createElement("pedidos");
            for (Pedido p : PedidoDAO.obtenerTodos()) {
                Element pedido = doc.createElement("pedido");
                pedido.setAttribute("id", String.valueOf(p.getIdPedido()));
                pedido.appendChild(crearElemento(doc, "fecha", p.getFecha().toString()));
                pedido.appendChild(crearElemento(doc, "id_cliente", String.valueOf(p.getIdCliente())));

                Element lineas = doc.createElement("lineas");
                var mapa = LineaPedidoDAO.obtenerCantidadPorProducto(p.getIdPedido());
                for (var entry : mapa.entrySet()) {
                    Producto producto = ProductoDAO.obtenerPorId(entry.getKey());
                    int cantidad = entry.getValue();

                    Element linea = doc.createElement("linea");
                    linea.setAttribute("producto", producto.getNombre());
                    linea.setAttribute("cantidad", String.valueOf(cantidad));
                    linea.setAttribute("precio_unitario", String.format(Locale.US, "%.2f", producto.getPrecio()));
                    lineas.appendChild(linea);
                }
                pedido.appendChild(lineas);
                pedidos.appendChild(pedido);
            }
            root.appendChild(pedidos);

            // Guardar XML con pretty print
            Transformer tf = TransformerFactory.newInstance().newTransformer();
            tf.setOutputProperty(OutputKeys.INDENT, "yes");
            tf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            tf.transform(new DOMSource(doc), new StreamResult(new File(RUTA_XML)));

            // Validar contra XSD
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(new File(RUTA_XSD));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(RUTA_XML)));

            // Log del evento
            LogDAO.insertar(new Log(java.time.LocalDateTime.now(), gestor.getCorreo(), "EXPORTACION", "Exportación de pedidos a XML completada"));
            JOptionPane.showMessageDialog(null, "Exportación completada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error durante la exportación: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static Element crearElemento(Document doc, String nombre, String valor) {
        Element e = doc.createElement(nombre);
        e.setTextContent(valor);
        return e;
    }
}
