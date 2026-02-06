package actions

import com.intellij.openapi.editor.Document
import com.intellij.openapi.fileEditor.FileDocumentManagerListener
import com.intellij.openapi.project.Project
import utils.formatFiles
import utils.resolvePsiFile
import utils.resolveUnsavedPsiFiles

class FileSaveListener(private val project: Project) : FileDocumentManagerListener {

    override fun beforeAllDocumentsSaving() {
        if (project.isDisposed) return
        val psiFiles = resolveUnsavedPsiFiles(project)
        if (psiFiles.isEmpty()) return
        formatFiles(project, psiFiles)
    }

    override fun beforeDocumentSaving(document: Document) {
        if (project.isDisposed) return
        val psiFile = resolvePsiFile(project, document) ?: return
        formatFiles(project, listOf(psiFile))
    }
}
