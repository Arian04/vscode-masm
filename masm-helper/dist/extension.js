"use strict";var v=Object.create;var r=Object.defineProperty;var h=Object.getOwnPropertyDescriptor;var k=Object.getOwnPropertyNames;var m=Object.getPrototypeOf,T=Object.prototype.hasOwnProperty;var f=(s,e)=>{for(var t in e)r(s,t,{get:e[t],enumerable:!0})},d=(s,e,t,a)=>{if(e&&typeof e=="object"||typeof e=="function")for(let i of k(e))!T.call(s,i)&&i!==t&&r(s,i,{get:()=>e[i],enumerable:!(a=h(e,i))||a.enumerable});return s};var c=(s,e,t)=>(t=s!=null?v(m(s)):{},d(e||!s||!s.__esModule?r(t,"default",{value:s,enumerable:!0}):t,s)),S=s=>d(r({},"__esModule",{value:!0}),s);var P={};f(P,{activate:()=>g,deactivate:()=>E});module.exports=S(P);var u=c(require("vscode"));var o=c(require("vscode")),p=c(require("path")),n=class s{static TaskType="masm";scriptName="masm.ps1";scriptPath;taskSource;definition={type:s.TaskType};defaultShellOptions;presentationOptions={echo:!1,reveal:o.TaskRevealKind.Always,focus:!1,panel:o.TaskPanelKind.Shared,showReuseMessage:!1,clear:!0};resourcesPath;constructor(e,t){this.taskSource=e,this.resourcesPath=t,this.scriptPath=p.join(this.resourcesPath,this.scriptName),this.defaultShellOptions={executable:"powershell",shellArgs:["-ExecutionPolicy","Bypass","-NoProfile","-File",`${this.scriptPath}`],env:{VS_INSTALL_DIR:"auto-detect",EXTRA_LIB_PATH:"auto-download"}}}basenameNoExtension(e){return e.split(/[\\/]/).pop()?.replace(/\.[^/.]+$/,"")??""}makeScriptExecution(e){return new o.ShellExecution(e.join(" "),this.defaultShellOptions)}makeTask(e,t){let a=new o.Task(this.definition,o.TaskScope.Workspace,e,this.taskSource,this.makeScriptExecution(t));return a.presentationOptions=this.presentationOptions,a.group=o.TaskGroup.Build,a}provideTasks(e){let t=o.window.activeTextEditor;if(!t){console.log("currentActiveTextEditor is undefined");return}let a=t.document.fileName,i=this.basenameNoExtension(a);return[this.makeTask("Build",["build",`"${a}"`,`"${i}"`]),this.makeTask("Build + Run",["buildrun",`"${a}"`,`"${i}"`]),this.makeTask("Debug",["debug",`"${a}"`,`"${i}"`])]}resolveTask(e,t){return e}};var x="resources",l;function g(s){let e=s.asAbsolutePath(x);l=u.tasks.registerTaskProvider(n.TaskType,new n(s.extension.packageJSON.name,e))}function E(){l&&l.dispose()}0&&(module.exports={activate,deactivate});
