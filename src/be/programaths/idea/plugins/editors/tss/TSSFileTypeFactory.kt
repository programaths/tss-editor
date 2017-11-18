package be.programaths.idea.plugins.editors.tss

import com.intellij.openapi.fileTypes.FileTypeConsumer
import com.intellij.openapi.fileTypes.FileTypeFactory

class TSSFileTypeFactory : FileTypeFactory() {
    override fun createFileTypes(p0: FileTypeConsumer) {
        p0.consume(tssFileType,tssFileType.EXT)
    }
}