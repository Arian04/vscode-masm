@file:JsModule("vscode")
@file:JsQualifier("languages")
@file:Suppress(
    "INTERFACE_WITH_SUPERCLASS",
    "OVERRIDING_FINAL_MEMBER",
    "RETURN_TYPE_MISMATCH_ON_OVERRIDE",
    "CONFLICTING_OVERLOADS"
)

package vscode.languages

import vscode.*

external fun getLanguages(): Thenable<Array<String>>

external fun setTextDocumentLanguage(document: TextDocument, languageId: String): Thenable<TextDocument>

external fun match(selector: DocumentFilter, document: TextDocument): Number

external fun match(selector: String, document: TextDocument): Number

external fun match(selector: Array<Any /* DocumentFilter | String */>, document: TextDocument): Number

external var onDidChangeDiagnostics: Event<DiagnosticChangeEvent>

external fun getDiagnostics(resource: Uri): Array<Diagnostic>

external fun getDiagnostics(): Array<dynamic /* JsTuple<Uri, Array<Diagnostic>> */>

external fun createDiagnosticCollection(name: String = definedExternally): DiagnosticCollection

external fun createLanguageStatusItem(id: String, selector: DocumentFilter): LanguageStatusItem

external fun createLanguageStatusItem(id: String, selector: String): LanguageStatusItem

external fun createLanguageStatusItem(
    id: String,
    selector: Array<Any /* DocumentFilter | String */>
): LanguageStatusItem

external fun registerCompletionItemProvider(
    selector: DocumentFilter,
    provider: CompletionItemProvider__0,
    vararg triggerCharacters: String
): Disposable

external fun registerCompletionItemProvider(
    selector: String,
    provider: CompletionItemProvider__0,
    vararg triggerCharacters: String
): Disposable

external fun registerCompletionItemProvider(
    selector: Array<Any /* DocumentFilter | String */>,
    provider: CompletionItemProvider__0,
    vararg triggerCharacters: String
): Disposable

external fun registerInlineCompletionItemProvider(
    selector: DocumentFilter,
    provider: InlineCompletionItemProvider
): Disposable

external fun registerInlineCompletionItemProvider(selector: String, provider: InlineCompletionItemProvider): Disposable

external fun registerInlineCompletionItemProvider(
    selector: Array<Any /* DocumentFilter | String */>,
    provider: InlineCompletionItemProvider
): Disposable

external fun registerCodeActionsProvider(
    selector: DocumentFilter,
    provider: CodeActionProvider__0,
    metadata: CodeActionProviderMetadata = definedExternally
): Disposable

external fun registerCodeActionsProvider(selector: DocumentFilter, provider: CodeActionProvider__0): Disposable

external fun registerCodeActionsProvider(
    selector: String,
    provider: CodeActionProvider__0,
    metadata: CodeActionProviderMetadata = definedExternally
): Disposable

external fun registerCodeActionsProvider(selector: String, provider: CodeActionProvider__0): Disposable

external fun registerCodeActionsProvider(
    selector: Array<Any /* DocumentFilter | String */>,
    provider: CodeActionProvider__0,
    metadata: CodeActionProviderMetadata = definedExternally
): Disposable

external fun registerCodeActionsProvider(
    selector: Array<Any /* DocumentFilter | String */>,
    provider: CodeActionProvider__0
): Disposable

external fun registerCodeLensProvider(selector: DocumentFilter, provider: CodeLensProvider__0): Disposable

external fun registerCodeLensProvider(selector: String, provider: CodeLensProvider__0): Disposable

external fun registerCodeLensProvider(
    selector: Array<Any /* DocumentFilter | String */>,
    provider: CodeLensProvider__0
): Disposable

external fun registerDefinitionProvider(selector: DocumentFilter, provider: DefinitionProvider): Disposable

external fun registerDefinitionProvider(selector: String, provider: DefinitionProvider): Disposable

external fun registerDefinitionProvider(
    selector: Array<Any /* DocumentFilter | String */>,
    provider: DefinitionProvider
): Disposable

external fun registerImplementationProvider(selector: DocumentFilter, provider: ImplementationProvider): Disposable

external fun registerImplementationProvider(selector: String, provider: ImplementationProvider): Disposable

external fun registerImplementationProvider(
    selector: Array<Any /* DocumentFilter | String */>,
    provider: ImplementationProvider
): Disposable

external fun registerTypeDefinitionProvider(selector: DocumentFilter, provider: TypeDefinitionProvider): Disposable

external fun registerTypeDefinitionProvider(selector: String, provider: TypeDefinitionProvider): Disposable

external fun registerTypeDefinitionProvider(
    selector: Array<Any /* DocumentFilter | String */>,
    provider: TypeDefinitionProvider
): Disposable

external fun registerDeclarationProvider(selector: DocumentFilter, provider: DeclarationProvider): Disposable

external fun registerDeclarationProvider(selector: String, provider: DeclarationProvider): Disposable

external fun registerDeclarationProvider(
    selector: Array<Any /* DocumentFilter | String */>,
    provider: DeclarationProvider
): Disposable

external fun registerHoverProvider(selector: DocumentFilter, provider: HoverProvider): Disposable

external fun registerHoverProvider(selector: String, provider: HoverProvider): Disposable

external fun registerHoverProvider(
    selector: Array<Any /* DocumentFilter | String */>,
    provider: HoverProvider
): Disposable

external fun registerEvaluatableExpressionProvider(
    selector: DocumentFilter,
    provider: EvaluatableExpressionProvider
): Disposable

external fun registerEvaluatableExpressionProvider(
    selector: String,
    provider: EvaluatableExpressionProvider
): Disposable

external fun registerEvaluatableExpressionProvider(
    selector: Array<Any /* DocumentFilter | String */>,
    provider: EvaluatableExpressionProvider
): Disposable

external fun registerInlineValuesProvider(selector: DocumentFilter, provider: InlineValuesProvider): Disposable

external fun registerInlineValuesProvider(selector: String, provider: InlineValuesProvider): Disposable

external fun registerInlineValuesProvider(
    selector: Array<Any /* DocumentFilter | String */>,
    provider: InlineValuesProvider
): Disposable

external fun registerDocumentHighlightProvider(
    selector: DocumentFilter,
    provider: DocumentHighlightProvider
): Disposable

external fun registerDocumentHighlightProvider(selector: String, provider: DocumentHighlightProvider): Disposable

external fun registerDocumentHighlightProvider(
    selector: Array<Any /* DocumentFilter | String */>,
    provider: DocumentHighlightProvider
): Disposable

external fun registerDocumentSymbolProvider(
    selector: DocumentFilter,
    provider: DocumentSymbolProvider,
    metaData: DocumentSymbolProviderMetadata = definedExternally
): Disposable

external fun registerDocumentSymbolProvider(selector: DocumentFilter, provider: DocumentSymbolProvider): Disposable

external fun registerDocumentSymbolProvider(
    selector: String,
    provider: DocumentSymbolProvider,
    metaData: DocumentSymbolProviderMetadata = definedExternally
): Disposable

external fun registerDocumentSymbolProvider(selector: String, provider: DocumentSymbolProvider): Disposable

external fun registerDocumentSymbolProvider(
    selector: Array<Any /* DocumentFilter | String */>,
    provider: DocumentSymbolProvider,
    metaData: DocumentSymbolProviderMetadata = definedExternally
): Disposable

external fun registerDocumentSymbolProvider(
    selector: Array<Any /* DocumentFilter | String */>,
    provider: DocumentSymbolProvider
): Disposable

external fun registerWorkspaceSymbolProvider(provider: WorkspaceSymbolProvider__0): Disposable

external fun registerReferenceProvider(selector: DocumentFilter, provider: ReferenceProvider): Disposable

external fun registerReferenceProvider(selector: String, provider: ReferenceProvider): Disposable

external fun registerReferenceProvider(
    selector: Array<Any /* DocumentFilter | String */>,
    provider: ReferenceProvider
): Disposable

external fun registerRenameProvider(selector: DocumentFilter, provider: RenameProvider): Disposable

external fun registerRenameProvider(selector: String, provider: RenameProvider): Disposable

external fun registerRenameProvider(
    selector: Array<Any /* DocumentFilter | String */>,
    provider: RenameProvider
): Disposable

external fun registerDocumentSemanticTokensProvider(
    selector: DocumentFilter,
    provider: DocumentSemanticTokensProvider,
    legend: SemanticTokensLegend
): Disposable

external fun registerDocumentSemanticTokensProvider(
    selector: String,
    provider: DocumentSemanticTokensProvider,
    legend: SemanticTokensLegend
): Disposable

external fun registerDocumentSemanticTokensProvider(
    selector: Array<Any /* DocumentFilter | String */>,
    provider: DocumentSemanticTokensProvider,
    legend: SemanticTokensLegend
): Disposable

external fun registerDocumentRangeSemanticTokensProvider(
    selector: DocumentFilter,
    provider: DocumentRangeSemanticTokensProvider,
    legend: SemanticTokensLegend
): Disposable

external fun registerDocumentRangeSemanticTokensProvider(
    selector: String,
    provider: DocumentRangeSemanticTokensProvider,
    legend: SemanticTokensLegend
): Disposable

external fun registerDocumentRangeSemanticTokensProvider(
    selector: Array<Any /* DocumentFilter | String */>,
    provider: DocumentRangeSemanticTokensProvider,
    legend: SemanticTokensLegend
): Disposable

external fun registerDocumentFormattingEditProvider(
    selector: DocumentFilter,
    provider: DocumentFormattingEditProvider
): Disposable

external fun registerDocumentFormattingEditProvider(
    selector: String,
    provider: DocumentFormattingEditProvider
): Disposable

external fun registerDocumentFormattingEditProvider(
    selector: Array<Any /* DocumentFilter | String */>,
    provider: DocumentFormattingEditProvider
): Disposable

external fun registerDocumentRangeFormattingEditProvider(
    selector: DocumentFilter,
    provider: DocumentRangeFormattingEditProvider
): Disposable

external fun registerDocumentRangeFormattingEditProvider(
    selector: String,
    provider: DocumentRangeFormattingEditProvider
): Disposable

external fun registerDocumentRangeFormattingEditProvider(
    selector: Array<Any /* DocumentFilter | String */>,
    provider: DocumentRangeFormattingEditProvider
): Disposable

external fun registerOnTypeFormattingEditProvider(
    selector: DocumentFilter,
    provider: OnTypeFormattingEditProvider,
    firstTriggerCharacter: String,
    vararg moreTriggerCharacter: String
): Disposable

external fun registerOnTypeFormattingEditProvider(
    selector: String,
    provider: OnTypeFormattingEditProvider,
    firstTriggerCharacter: String,
    vararg moreTriggerCharacter: String
): Disposable

external fun registerOnTypeFormattingEditProvider(
    selector: Array<Any /* DocumentFilter | String */>,
    provider: OnTypeFormattingEditProvider,
    firstTriggerCharacter: String,
    vararg moreTriggerCharacter: String
): Disposable

external fun registerSignatureHelpProvider(
    selector: DocumentFilter,
    provider: SignatureHelpProvider,
    vararg triggerCharacters: String
): Disposable

external fun registerSignatureHelpProvider(
    selector: String,
    provider: SignatureHelpProvider,
    vararg triggerCharacters: String
): Disposable

external fun registerSignatureHelpProvider(
    selector: Array<Any /* DocumentFilter | String */>,
    provider: SignatureHelpProvider,
    vararg triggerCharacters: String
): Disposable

external fun registerSignatureHelpProvider(
    selector: DocumentFilter,
    provider: SignatureHelpProvider,
    metadata: SignatureHelpProviderMetadata
): Disposable

external fun registerSignatureHelpProvider(
    selector: String,
    provider: SignatureHelpProvider,
    metadata: SignatureHelpProviderMetadata
): Disposable

external fun registerSignatureHelpProvider(
    selector: Array<Any /* DocumentFilter | String */>,
    provider: SignatureHelpProvider,
    metadata: SignatureHelpProviderMetadata
): Disposable

external fun registerDocumentLinkProvider(selector: DocumentFilter, provider: DocumentLinkProvider__0): Disposable

external fun registerDocumentLinkProvider(selector: String, provider: DocumentLinkProvider__0): Disposable

external fun registerDocumentLinkProvider(
    selector: Array<Any /* DocumentFilter | String */>,
    provider: DocumentLinkProvider__0
): Disposable

external fun registerColorProvider(selector: DocumentFilter, provider: DocumentColorProvider): Disposable

external fun registerColorProvider(selector: String, provider: DocumentColorProvider): Disposable

external fun registerColorProvider(
    selector: Array<Any /* DocumentFilter | String */>,
    provider: DocumentColorProvider
): Disposable

external fun registerInlayHintsProvider(selector: DocumentFilter, provider: InlayHintsProvider__0): Disposable

external fun registerInlayHintsProvider(selector: String, provider: InlayHintsProvider__0): Disposable

external fun registerInlayHintsProvider(
    selector: Array<Any /* DocumentFilter | String */>,
    provider: InlayHintsProvider__0
): Disposable

external fun registerFoldingRangeProvider(selector: DocumentFilter, provider: FoldingRangeProvider): Disposable

external fun registerFoldingRangeProvider(selector: String, provider: FoldingRangeProvider): Disposable

external fun registerFoldingRangeProvider(
    selector: Array<Any /* DocumentFilter | String */>,
    provider: FoldingRangeProvider
): Disposable

external fun registerSelectionRangeProvider(selector: DocumentFilter, provider: SelectionRangeProvider): Disposable

external fun registerSelectionRangeProvider(selector: String, provider: SelectionRangeProvider): Disposable

external fun registerSelectionRangeProvider(
    selector: Array<Any /* DocumentFilter | String */>,
    provider: SelectionRangeProvider
): Disposable

external fun registerCallHierarchyProvider(selector: DocumentFilter, provider: CallHierarchyProvider): Disposable

external fun registerCallHierarchyProvider(selector: String, provider: CallHierarchyProvider): Disposable

external fun registerCallHierarchyProvider(
    selector: Array<Any /* DocumentFilter | String */>,
    provider: CallHierarchyProvider
): Disposable

external fun registerTypeHierarchyProvider(selector: DocumentFilter, provider: TypeHierarchyProvider): Disposable

external fun registerTypeHierarchyProvider(selector: String, provider: TypeHierarchyProvider): Disposable

external fun registerTypeHierarchyProvider(
    selector: Array<Any /* DocumentFilter | String */>,
    provider: TypeHierarchyProvider
): Disposable

external fun registerLinkedEditingRangeProvider(
    selector: DocumentFilter,
    provider: LinkedEditingRangeProvider
): Disposable

external fun registerLinkedEditingRangeProvider(selector: String, provider: LinkedEditingRangeProvider): Disposable

external fun registerLinkedEditingRangeProvider(
    selector: Array<Any /* DocumentFilter | String */>,
    provider: LinkedEditingRangeProvider
): Disposable

external fun registerDocumentDropEditProvider(selector: DocumentFilter, provider: DocumentDropEditProvider): Disposable

external fun registerDocumentDropEditProvider(selector: String, provider: DocumentDropEditProvider): Disposable

external fun registerDocumentDropEditProvider(
    selector: Array<Any /* DocumentFilter | String */>,
    provider: DocumentDropEditProvider
): Disposable

external fun setLanguageConfiguration(language: String, configuration: LanguageConfiguration): Disposable
