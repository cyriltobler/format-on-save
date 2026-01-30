package actions

import com.intellij.openapi.editor.Document
import com.intellij.openapi.fileEditor.FileDocumentManagerListener
import utils.formatCode

class FileSaveListener : FileDocumentManagerListener {
    override fun beforeDocumentSaving(document: Document) {
        formatCode(document)
    }
}
