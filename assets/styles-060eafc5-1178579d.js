import{$ as F,a as Ht,i as Xt,l as Kt,r as Jt,g as Vt,f as Wt,w as qt,M as x,K as lt,s as Qt,aA as Zt}from"./mermaid.esm.min-b3d8c51a.js";var St=function(){var t=function(T,s,r,o){for(r=r||{},o=T.length;o--;r[T[o]]=s);return r},i=[1,2],n=[1,3],l=[1,5],h=[1,7],d=[2,5],y=[1,15],v=[1,17],p=[1,21],_=[1,22],G=[1,23],j=[1,24],O=[1,37],Y=[1,25],U=[1,26],z=[1,27],M=[1,28],H=[1,29],X=[1,32],K=[1,33],J=[1,34],V=[1,35],W=[1,36],q=[1,39],Q=[1,40],Z=[1,41],tt=[1,42],R=[1,38],$t=[1,45],c=[1,4,5,16,17,19,21,22,24,25,26,27,28,29,33,35,37,38,42,50,51,52,53,56,60],et=[1,4,5,14,15,16,17,19,21,22,24,25,26,27,28,29,33,35,37,38,42,50,51,52,53,56,60],ht=[1,4,5,7,16,17,19,21,22,24,25,26,27,28,29,33,35,37,38,42,50,51,52,53,56,60],Dt=[4,5,16,17,19,21,22,24,25,26,27,28,29,33,35,37,38,42,50,51,52,53,56,60],ut={trace:function(){},yy:{},symbols_:{error:2,start:3,SPACE:4,NL:5,directive:6,SD:7,document:8,line:9,statement:10,classDefStatement:11,cssClassStatement:12,idStatement:13,DESCR:14,"-->":15,HIDE_EMPTY:16,scale:17,WIDTH:18,COMPOSIT_STATE:19,STRUCT_START:20,STRUCT_STOP:21,STATE_DESCR:22,AS:23,ID:24,FORK:25,JOIN:26,CHOICE:27,CONCURRENT:28,note:29,notePosition:30,NOTE_TEXT:31,direction:32,acc_title:33,acc_title_value:34,acc_descr:35,acc_descr_value:36,acc_descr_multiline_value:37,classDef:38,CLASSDEF_ID:39,CLASSDEF_STYLEOPTS:40,DEFAULT:41,class:42,CLASSENTITY_IDS:43,STYLECLASS:44,openDirective:45,typeDirective:46,closeDirective:47,":":48,argDirective:49,direction_tb:50,direction_bt:51,direction_rl:52,direction_lr:53,eol:54,";":55,EDGE_STATE:56,STYLE_SEPARATOR:57,left_of:58,right_of:59,open_directive:60,type_directive:61,arg_directive:62,close_directive:63,$accept:0,$end:1},terminals_:{2:"error",4:"SPACE",5:"NL",7:"SD",14:"DESCR",15:"-->",16:"HIDE_EMPTY",17:"scale",18:"WIDTH",19:"COMPOSIT_STATE",20:"STRUCT_START",21:"STRUCT_STOP",22:"STATE_DESCR",23:"AS",24:"ID",25:"FORK",26:"JOIN",27:"CHOICE",28:"CONCURRENT",29:"note",31:"NOTE_TEXT",33:"acc_title",34:"acc_title_value",35:"acc_descr",36:"acc_descr_value",37:"acc_descr_multiline_value",38:"classDef",39:"CLASSDEF_ID",40:"CLASSDEF_STYLEOPTS",41:"DEFAULT",42:"class",43:"CLASSENTITY_IDS",44:"STYLECLASS",48:":",50:"direction_tb",51:"direction_bt",52:"direction_rl",53:"direction_lr",55:";",56:"EDGE_STATE",57:"STYLE_SEPARATOR",58:"left_of",59:"right_of",60:"open_directive",61:"type_directive",62:"arg_directive",63:"close_directive"},productions_:[0,[3,2],[3,2],[3,2],[3,2],[8,0],[8,2],[9,2],[9,1],[9,1],[10,1],[10,1],[10,1],[10,2],[10,3],[10,4],[10,1],[10,2],[10,1],[10,4],[10,3],[10,6],[10,1],[10,1],[10,1],[10,1],[10,4],[10,4],[10,1],[10,1],[10,2],[10,2],[10,1],[11,3],[11,3],[12,3],[6,3],[6,5],[32,1],[32,1],[32,1],[32,1],[54,1],[54,1],[13,1],[13,1],[13,3],[13,3],[30,1],[30,1],[45,1],[46,1],[49,1],[47,1]],performAction:function(T,s,r,o,u,e,m){var a=e.length-1;switch(u){case 4:return o.setRootDoc(e[a]),e[a];case 5:this.$=[];break;case 6:e[a]!="nl"&&(e[a-1].push(e[a]),this.$=e[a-1]);break;case 7:case 8:this.$=e[a];break;case 9:this.$="nl";break;case 12:this.$=e[a];break;case 13:const B=e[a-1];B.description=o.trimColon(e[a]),this.$=B;break;case 14:this.$={stmt:"relation",state1:e[a-2],state2:e[a]};break;case 15:const dt=o.trimColon(e[a]);this.$={stmt:"relation",state1:e[a-3],state2:e[a-1],description:dt};break;case 19:this.$={stmt:"state",id:e[a-3],type:"default",description:"",doc:e[a-1]};break;case 20:var $=e[a],I=e[a-2].trim();if(e[a].match(":")){var it=e[a].split(":");$=it[0],I=[I,it[1]]}this.$={stmt:"state",id:$,type:"default",description:I};break;case 21:this.$={stmt:"state",id:e[a-3],type:"default",description:e[a-5],doc:e[a-1]};break;case 22:this.$={stmt:"state",id:e[a],type:"fork"};break;case 23:this.$={stmt:"state",id:e[a],type:"join"};break;case 24:this.$={stmt:"state",id:e[a],type:"choice"};break;case 25:this.$={stmt:"state",id:o.getDividerId(),type:"divider"};break;case 26:this.$={stmt:"state",id:e[a-1].trim(),note:{position:e[a-2].trim(),text:e[a].trim()}};break;case 30:this.$=e[a].trim(),o.setAccTitle(this.$);break;case 31:case 32:this.$=e[a].trim(),o.setAccDescription(this.$);break;case 33:case 34:this.$={stmt:"classDef",id:e[a-1].trim(),classes:e[a].trim()};break;case 35:this.$={stmt:"applyClass",id:e[a-1].trim(),styleClass:e[a].trim()};break;case 38:o.setDirection("TB"),this.$={stmt:"dir",value:"TB"};break;case 39:o.setDirection("BT"),this.$={stmt:"dir",value:"BT"};break;case 40:o.setDirection("RL"),this.$={stmt:"dir",value:"RL"};break;case 41:o.setDirection("LR"),this.$={stmt:"dir",value:"LR"};break;case 44:case 45:this.$={stmt:"state",id:e[a].trim(),type:"default",description:""};break;case 46:this.$={stmt:"state",id:e[a-2].trim(),classes:[e[a].trim()],type:"default",description:""};break;case 47:this.$={stmt:"state",id:e[a-2].trim(),classes:[e[a].trim()],type:"default",description:""};break;case 50:o.parseDirective("%%{","open_directive");break;case 51:o.parseDirective(e[a],"type_directive");break;case 52:e[a]=e[a].trim().replace(/'/g,'"'),o.parseDirective(e[a],"arg_directive");break;case 53:o.parseDirective("}%%","close_directive","state");break}},table:[{3:1,4:i,5:n,6:4,7:l,45:6,60:h},{1:[3]},{3:8,4:i,5:n,6:4,7:l,45:6,60:h},{3:9,4:i,5:n,6:4,7:l,45:6,60:h},{3:10,4:i,5:n,6:4,7:l,45:6,60:h},t([1,4,5,16,17,19,22,24,25,26,27,28,29,33,35,37,38,42,50,51,52,53,56,60],d,{8:11}),{46:12,61:[1,13]},{61:[2,50]},{1:[2,1]},{1:[2,2]},{1:[2,3]},{1:[2,4],4:y,5:v,6:30,9:14,10:16,11:18,12:19,13:20,16:p,17:_,19:G,22:j,24:O,25:Y,26:U,27:z,28:M,29:H,32:31,33:X,35:K,37:J,38:V,42:W,45:6,50:q,51:Q,52:Z,53:tt,56:R,60:h},{47:43,48:[1,44],63:$t},t([48,63],[2,51]),t(c,[2,6]),{6:30,10:46,11:18,12:19,13:20,16:p,17:_,19:G,22:j,24:O,25:Y,26:U,27:z,28:M,29:H,32:31,33:X,35:K,37:J,38:V,42:W,45:6,50:q,51:Q,52:Z,53:tt,56:R,60:h},t(c,[2,8]),t(c,[2,9]),t(c,[2,10]),t(c,[2,11]),t(c,[2,12],{14:[1,47],15:[1,48]}),t(c,[2,16]),{18:[1,49]},t(c,[2,18],{20:[1,50]}),{23:[1,51]},t(c,[2,22]),t(c,[2,23]),t(c,[2,24]),t(c,[2,25]),{30:52,31:[1,53],58:[1,54],59:[1,55]},t(c,[2,28]),t(c,[2,29]),{34:[1,56]},{36:[1,57]},t(c,[2,32]),{39:[1,58],41:[1,59]},{43:[1,60]},t(et,[2,44],{57:[1,61]}),t(et,[2,45],{57:[1,62]}),t(c,[2,38]),t(c,[2,39]),t(c,[2,40]),t(c,[2,41]),t(ht,[2,36]),{49:63,62:[1,64]},t(ht,[2,53]),t(c,[2,7]),t(c,[2,13]),{13:65,24:O,56:R},t(c,[2,17]),t(Dt,d,{8:66}),{24:[1,67]},{24:[1,68]},{23:[1,69]},{24:[2,48]},{24:[2,49]},t(c,[2,30]),t(c,[2,31]),{40:[1,70]},{40:[1,71]},{44:[1,72]},{24:[1,73]},{24:[1,74]},{47:75,63:$t},{63:[2,52]},t(c,[2,14],{14:[1,76]}),{4:y,5:v,6:30,9:14,10:16,11:18,12:19,13:20,16:p,17:_,19:G,21:[1,77],22:j,24:O,25:Y,26:U,27:z,28:M,29:H,32:31,33:X,35:K,37:J,38:V,42:W,45:6,50:q,51:Q,52:Z,53:tt,56:R,60:h},t(c,[2,20],{20:[1,78]}),{31:[1,79]},{24:[1,80]},t(c,[2,33]),t(c,[2,34]),t(c,[2,35]),t(et,[2,46]),t(et,[2,47]),t(ht,[2,37]),t(c,[2,15]),t(c,[2,19]),t(Dt,d,{8:81}),t(c,[2,26]),t(c,[2,27]),{4:y,5:v,6:30,9:14,10:16,11:18,12:19,13:20,16:p,17:_,19:G,21:[1,82],22:j,24:O,25:Y,26:U,27:z,28:M,29:H,32:31,33:X,35:K,37:J,38:V,42:W,45:6,50:q,51:Q,52:Z,53:tt,56:R,60:h},t(c,[2,21])],defaultActions:{7:[2,50],8:[2,1],9:[2,2],10:[2,3],54:[2,48],55:[2,49],64:[2,52]},parseError:function(T,s){if(s.recoverable)this.trace(T);else{var r=new Error(T);throw r.hash=s,r}},parse:function(T){var s=this,r=[0],o=[],u=[null],e=[],m=this.table,a="",$=0,I=0,it=2,B=1,dt=e.slice.call(arguments,1),f=Object.create(this.lexer),D={yy:{}};for(var yt in this.yy)Object.prototype.hasOwnProperty.call(this.yy,yt)&&(D.yy[yt]=this.yy[yt]);f.setInput(T,D.yy),D.yy.lexer=f,D.yy.parser=this,typeof f.yylloc>"u"&&(f.yylloc={});var ft=f.yylloc;e.push(ft);var zt=f.options&&f.options.ranges;typeof D.yy.parseError=="function"?this.parseError=D.yy.parseError:this.parseError=Object.getPrototypeOf(this).parseError;function Mt(){var E;return E=o.pop()||f.lex()||B,typeof E!="number"&&(E instanceof Array&&(o=E,E=o.pop()),E=s.symbols_[E]||E),E}for(var S,C,k,gt,L={},st,b,Ct,rt;;){if(C=r[r.length-1],this.defaultActions[C]?k=this.defaultActions[C]:((S===null||typeof S>"u")&&(S=Mt()),k=m[C]&&m[C][S]),typeof k>"u"||!k.length||!k[0]){var mt="";rt=[];for(st in m[C])this.terminals_[st]&&st>it&&rt.push("'"+this.terminals_[st]+"'");f.showPosition?mt="Parse error on line "+($+1)+`:
`+f.showPosition()+`
Expecting `+rt.join(", ")+", got '"+(this.terminals_[S]||S)+"'":mt="Parse error on line "+($+1)+": Unexpected "+(S==B?"end of input":"'"+(this.terminals_[S]||S)+"'"),this.parseError(mt,{text:f.match,token:this.terminals_[S]||S,line:f.yylineno,loc:ft,expected:rt})}if(k[0]instanceof Array&&k.length>1)throw new Error("Parse Error: multiple actions possible at state: "+C+", token: "+S);switch(k[0]){case 1:r.push(S),u.push(f.yytext),e.push(f.yylloc),r.push(k[1]),S=null,I=f.yyleng,a=f.yytext,$=f.yylineno,ft=f.yylloc;break;case 2:if(b=this.productions_[k[1]][1],L.$=u[u.length-b],L._$={first_line:e[e.length-(b||1)].first_line,last_line:e[e.length-1].last_line,first_column:e[e.length-(b||1)].first_column,last_column:e[e.length-1].last_column},zt&&(L._$.range=[e[e.length-(b||1)].range[0],e[e.length-1].range[1]]),gt=this.performAction.apply(L,[a,I,$,D.yy,k[1],u,e].concat(dt)),typeof gt<"u")return gt;b&&(r=r.slice(0,-1*b*2),u=u.slice(0,-1*b),e=e.slice(0,-1*b)),r.push(this.productions_[k[1]][0]),u.push(L.$),e.push(L._$),Ct=m[r[r.length-2]][r[r.length-1]],r.push(Ct);break;case 3:return!0}}return!0}},Ut=function(){var T={EOF:1,parseError:function(s,r){if(this.yy.parser)this.yy.parser.parseError(s,r);else throw new Error(s)},setInput:function(s,r){return this.yy=r||this.yy||{},this._input=s,this._more=this._backtrack=this.done=!1,this.yylineno=this.yyleng=0,this.yytext=this.matched=this.match="",this.conditionStack=["INITIAL"],this.yylloc={first_line:1,first_column:0,last_line:1,last_column:0},this.options.ranges&&(this.yylloc.range=[0,0]),this.offset=0,this},input:function(){var s=this._input[0];this.yytext+=s,this.yyleng++,this.offset++,this.match+=s,this.matched+=s;var r=s.match(/(?:\r\n?|\n).*/g);return r?(this.yylineno++,this.yylloc.last_line++):this.yylloc.last_column++,this.options.ranges&&this.yylloc.range[1]++,this._input=this._input.slice(1),s},unput:function(s){var r=s.length,o=s.split(/(?:\r\n?|\n)/g);this._input=s+this._input,this.yytext=this.yytext.substr(0,this.yytext.length-r),this.offset-=r;var u=this.match.split(/(?:\r\n?|\n)/g);this.match=this.match.substr(0,this.match.length-1),this.matched=this.matched.substr(0,this.matched.length-1),o.length-1&&(this.yylineno-=o.length-1);var e=this.yylloc.range;return this.yylloc={first_line:this.yylloc.first_line,last_line:this.yylineno+1,first_column:this.yylloc.first_column,last_column:o?(o.length===u.length?this.yylloc.first_column:0)+u[u.length-o.length].length-o[0].length:this.yylloc.first_column-r},this.options.ranges&&(this.yylloc.range=[e[0],e[0]+this.yyleng-r]),this.yyleng=this.yytext.length,this},more:function(){return this._more=!0,this},reject:function(){if(this.options.backtrack_lexer)this._backtrack=!0;else return this.parseError("Lexical error on line "+(this.yylineno+1)+`. You can only invoke reject() in the lexer when the lexer is of the backtracking persuasion (options.backtrack_lexer = true).
`+this.showPosition(),{text:"",token:null,line:this.yylineno});return this},less:function(s){this.unput(this.match.slice(s))},pastInput:function(){var s=this.matched.substr(0,this.matched.length-this.match.length);return(s.length>20?"...":"")+s.substr(-20).replace(/\n/g,"")},upcomingInput:function(){var s=this.match;return s.length<20&&(s+=this._input.substr(0,20-s.length)),(s.substr(0,20)+(s.length>20?"...":"")).replace(/\n/g,"")},showPosition:function(){var s=this.pastInput(),r=new Array(s.length+1).join("-");return s+this.upcomingInput()+`
`+r+"^"},test_match:function(s,r){var o,u,e;if(this.options.backtrack_lexer&&(e={yylineno:this.yylineno,yylloc:{first_line:this.yylloc.first_line,last_line:this.last_line,first_column:this.yylloc.first_column,last_column:this.yylloc.last_column},yytext:this.yytext,match:this.match,matches:this.matches,matched:this.matched,yyleng:this.yyleng,offset:this.offset,_more:this._more,_input:this._input,yy:this.yy,conditionStack:this.conditionStack.slice(0),done:this.done},this.options.ranges&&(e.yylloc.range=this.yylloc.range.slice(0))),u=s[0].match(/(?:\r\n?|\n).*/g),u&&(this.yylineno+=u.length),this.yylloc={first_line:this.yylloc.last_line,last_line:this.yylineno+1,first_column:this.yylloc.last_column,last_column:u?u[u.length-1].length-u[u.length-1].match(/\r?\n?/)[0].length:this.yylloc.last_column+s[0].length},this.yytext+=s[0],this.match+=s[0],this.matches=s,this.yyleng=this.yytext.length,this.options.ranges&&(this.yylloc.range=[this.offset,this.offset+=this.yyleng]),this._more=!1,this._backtrack=!1,this._input=this._input.slice(s[0].length),this.matched+=s[0],o=this.performAction.call(this,this.yy,this,r,this.conditionStack[this.conditionStack.length-1]),this.done&&this._input&&(this.done=!1),o)return o;if(this._backtrack){for(var m in e)this[m]=e[m];return!1}return!1},next:function(){if(this.done)return this.EOF;this._input||(this.done=!0);var s,r,o,u;this._more||(this.yytext="",this.match="");for(var e=this._currentRules(),m=0;m<e.length;m++)if(o=this._input.match(this.rules[e[m]]),o&&(!r||o[0].length>r[0].length)){if(r=o,u=m,this.options.backtrack_lexer){if(s=this.test_match(o,e[m]),s!==!1)return s;if(this._backtrack){r=!1;continue}else return!1}else if(!this.options.flex)break}return r?(s=this.test_match(r,e[u]),s!==!1?s:!1):this._input===""?this.EOF:this.parseError("Lexical error on line "+(this.yylineno+1)+`. Unrecognized text.
`+this.showPosition(),{text:"",token:null,line:this.yylineno})},lex:function(){var s=this.next();return s||this.lex()},begin:function(s){this.conditionStack.push(s)},popState:function(){var s=this.conditionStack.length-1;return s>0?this.conditionStack.pop():this.conditionStack[0]},_currentRules:function(){return this.conditionStack.length&&this.conditionStack[this.conditionStack.length-1]?this.conditions[this.conditionStack[this.conditionStack.length-1]].rules:this.conditions.INITIAL.rules},topState:function(s){return s=this.conditionStack.length-1-Math.abs(s||0),s>=0?this.conditionStack[s]:"INITIAL"},pushState:function(s){this.begin(s)},stateStackSize:function(){return this.conditionStack.length},options:{"case-insensitive":!0},performAction:function(s,r,o,u){switch(o){case 0:return 41;case 1:return 50;case 2:return 51;case 3:return 52;case 4:return 53;case 5:return this.begin("open_directive"),60;case 6:return this.begin("type_directive"),61;case 7:return this.popState(),this.begin("arg_directive"),48;case 8:return this.popState(),this.popState(),63;case 9:return 62;case 10:break;case 11:break;case 12:return 5;case 13:break;case 14:break;case 15:break;case 16:break;case 17:return this.pushState("SCALE"),17;case 18:return 18;case 19:this.popState();break;case 20:return this.begin("acc_title"),33;case 21:return this.popState(),"acc_title_value";case 22:return this.begin("acc_descr"),35;case 23:return this.popState(),"acc_descr_value";case 24:this.begin("acc_descr_multiline");break;case 25:this.popState();break;case 26:return"acc_descr_multiline_value";case 27:return this.pushState("CLASSDEF"),38;case 28:return this.popState(),this.pushState("CLASSDEFID"),"DEFAULT_CLASSDEF_ID";case 29:return this.popState(),this.pushState("CLASSDEFID"),39;case 30:return this.popState(),40;case 31:return this.pushState("CLASS"),42;case 32:return this.popState(),this.pushState("CLASS_STYLE"),43;case 33:return this.popState(),44;case 34:return this.pushState("SCALE"),17;case 35:return 18;case 36:this.popState();break;case 37:this.pushState("STATE");break;case 38:return this.popState(),r.yytext=r.yytext.slice(0,-8).trim(),25;case 39:return this.popState(),r.yytext=r.yytext.slice(0,-8).trim(),26;case 40:return this.popState(),r.yytext=r.yytext.slice(0,-10).trim(),27;case 41:return this.popState(),r.yytext=r.yytext.slice(0,-8).trim(),25;case 42:return this.popState(),r.yytext=r.yytext.slice(0,-8).trim(),26;case 43:return this.popState(),r.yytext=r.yytext.slice(0,-10).trim(),27;case 44:return 50;case 45:return 51;case 46:return 52;case 47:return 53;case 48:this.pushState("STATE_STRING");break;case 49:return this.pushState("STATE_ID"),"AS";case 50:return this.popState(),"ID";case 51:this.popState();break;case 52:return"STATE_DESCR";case 53:return 19;case 54:this.popState();break;case 55:return this.popState(),this.pushState("struct"),20;case 56:break;case 57:return this.popState(),21;case 58:break;case 59:return this.begin("NOTE"),29;case 60:return this.popState(),this.pushState("NOTE_ID"),58;case 61:return this.popState(),this.pushState("NOTE_ID"),59;case 62:this.popState(),this.pushState("FLOATING_NOTE");break;case 63:return this.popState(),this.pushState("FLOATING_NOTE_ID"),"AS";case 64:break;case 65:return"NOTE_TEXT";case 66:return this.popState(),"ID";case 67:return this.popState(),this.pushState("NOTE_TEXT"),24;case 68:return this.popState(),r.yytext=r.yytext.substr(2).trim(),31;case 69:return this.popState(),r.yytext=r.yytext.slice(0,-8).trim(),31;case 70:return 7;case 71:return 7;case 72:return 16;case 73:return 56;case 74:return 24;case 75:return r.yytext=r.yytext.trim(),14;case 76:return 15;case 77:return 28;case 78:return 57;case 79:return 5;case 80:return"INVALID"}},rules:[/^(?:default\b)/i,/^(?:.*direction\s+TB[^\n]*)/i,/^(?:.*direction\s+BT[^\n]*)/i,/^(?:.*direction\s+RL[^\n]*)/i,/^(?:.*direction\s+LR[^\n]*)/i,/^(?:%%\{)/i,/^(?:((?:(?!\}%%)[^:.])*))/i,/^(?::)/i,/^(?:\}%%)/i,/^(?:((?:(?!\}%%).|\n)*))/i,/^(?:%%(?!\{)[^\n]*)/i,/^(?:[^\}]%%[^\n]*)/i,/^(?:[\n]+)/i,/^(?:[\s]+)/i,/^(?:((?!\n)\s)+)/i,/^(?:#[^\n]*)/i,/^(?:%[^\n]*)/i,/^(?:scale\s+)/i,/^(?:\d+)/i,/^(?:\s+width\b)/i,/^(?:accTitle\s*:\s*)/i,/^(?:(?!\n||)*[^\n]*)/i,/^(?:accDescr\s*:\s*)/i,/^(?:(?!\n||)*[^\n]*)/i,/^(?:accDescr\s*\{\s*)/i,/^(?:[\}])/i,/^(?:[^\}]*)/i,/^(?:classDef\s+)/i,/^(?:DEFAULT\s+)/i,/^(?:\w+\s+)/i,/^(?:[^\n]*)/i,/^(?:class\s+)/i,/^(?:(\w+)+((,\s*\w+)*))/i,/^(?:[^\n]*)/i,/^(?:scale\s+)/i,/^(?:\d+)/i,/^(?:\s+width\b)/i,/^(?:state\s+)/i,/^(?:.*<<fork>>)/i,/^(?:.*<<join>>)/i,/^(?:.*<<choice>>)/i,/^(?:.*\[\[fork\]\])/i,/^(?:.*\[\[join\]\])/i,/^(?:.*\[\[choice\]\])/i,/^(?:.*direction\s+TB[^\n]*)/i,/^(?:.*direction\s+BT[^\n]*)/i,/^(?:.*direction\s+RL[^\n]*)/i,/^(?:.*direction\s+LR[^\n]*)/i,/^(?:["])/i,/^(?:\s*as\s+)/i,/^(?:[^\n\{]*)/i,/^(?:["])/i,/^(?:[^"]*)/i,/^(?:[^\n\s\{]+)/i,/^(?:\n)/i,/^(?:\{)/i,/^(?:%%(?!\{)[^\n]*)/i,/^(?:\})/i,/^(?:[\n])/i,/^(?:note\s+)/i,/^(?:left of\b)/i,/^(?:right of\b)/i,/^(?:")/i,/^(?:\s*as\s*)/i,/^(?:["])/i,/^(?:[^"]*)/i,/^(?:[^\n]*)/i,/^(?:\s*[^:\n\s\-]+)/i,/^(?:\s*:[^:\n;]+)/i,/^(?:[\s\S]*?end note\b)/i,/^(?:stateDiagram\s+)/i,/^(?:stateDiagram-v2\s+)/i,/^(?:hide empty description\b)/i,/^(?:\[\*\])/i,/^(?:[^:\n\s\-\{]+)/i,/^(?:\s*:[^:\n;]+)/i,/^(?:-->)/i,/^(?:--)/i,/^(?::::)/i,/^(?:$)/i,/^(?:.)/i],conditions:{LINE:{rules:[14,15],inclusive:!1},close_directive:{rules:[14,15],inclusive:!1},arg_directive:{rules:[8,9,14,15],inclusive:!1},type_directive:{rules:[7,8,14,15],inclusive:!1},open_directive:{rules:[6,14,15],inclusive:!1},struct:{rules:[14,15,27,31,37,44,45,46,47,56,57,58,59,73,74,75,76,77],inclusive:!1},FLOATING_NOTE_ID:{rules:[66],inclusive:!1},FLOATING_NOTE:{rules:[63,64,65],inclusive:!1},NOTE_TEXT:{rules:[68,69],inclusive:!1},NOTE_ID:{rules:[67],inclusive:!1},NOTE:{rules:[60,61,62],inclusive:!1},CLASS_STYLE:{rules:[33],inclusive:!1},CLASS:{rules:[32],inclusive:!1},CLASSDEFID:{rules:[30],inclusive:!1},CLASSDEF:{rules:[28,29],inclusive:!1},acc_descr_multiline:{rules:[25,26],inclusive:!1},acc_descr:{rules:[23],inclusive:!1},acc_title:{rules:[21],inclusive:!1},SCALE:{rules:[18,19,35,36],inclusive:!1},ALIAS:{rules:[],inclusive:!1},STATE_ID:{rules:[50],inclusive:!1},STATE_STRING:{rules:[51,52],inclusive:!1},FORK_STATE:{rules:[],inclusive:!1},STATE:{rules:[14,15,38,39,40,41,42,43,48,49,53,54,55],inclusive:!1},ID:{rules:[14,15],inclusive:!1},INITIAL:{rules:[0,1,2,3,4,5,10,11,12,13,15,16,17,20,22,24,27,31,34,37,55,59,70,71,72,73,74,75,76,78,79,80],inclusive:!0}}};return T}();ut.lexer=Ut;function pt(){this.yy={}}return pt.prototype=ut,ut.Parser=pt,new pt}();St.parser=St;const Ae=St,te="LR",Ie="TB",_t="state",Ot="relation",ee="classDef",ie="applyClass",Et="default",se="divider",xt="[*]",Rt="start",Bt=xt,wt="end",At="color",It="fill",re="bgFill",ne=",";function Nt(){return{}}let Pt=te,ot=[],w=Nt();const Ft=()=>({relations:[],states:{},documents:{}});let ct={root:Ft()},g=ct.root,N=0,Lt=0;const ae={LINE:0,DOTTED_LINE:1},oe={AGGREGATION:0,EXTENSION:1,COMPOSITION:2,DEPENDENCY:3},nt=t=>JSON.parse(JSON.stringify(t)),ce=function(t,i,n){qt.parseDirective(this,t,i,n)},le=t=>{x.info("Setting root doc",t),ot=t},he=()=>ot,at=(t,i,n)=>{if(i.stmt===Ot)at(t,i.state1,!0),at(t,i.state2,!1);else if(i.stmt===_t&&(i.id==="[*]"?(i.id=n?t.id+"_start":t.id+"_end",i.start=n):i.id=i.id.trim()),i.doc){const l=[];let h=[],d;for(d=0;d<i.doc.length;d++)if(i.doc[d].type===se){const y=nt(i.doc[d]);y.doc=nt(h),l.push(y),h=[]}else h.push(i.doc[d]);if(l.length>0&&h.length>0){const y={stmt:_t,id:Zt(),type:"divider",doc:nt(h)};l.push(nt(y)),i.doc=l}i.doc.forEach(y=>at(i,y,!0))}},ue=()=>(at({id:"root"},{id:"root",doc:ot},!0),{id:"root",doc:ot}),pe=t=>{let i;t.doc?i=t.doc:i=t,x.info(i),Gt(!0),x.info("Extract",i),i.forEach(n=>{switch(n.stmt){case _t:A(n.id.trim(),n.type,n.doc,n.description,n.note,n.classes,n.styles,n.textStyles);break;case Ot:jt(n.state1,n.state2,n.description);break;case ee:Yt(n.id.trim(),n.classes);break;case ie:vt(n.id.trim(),n.styleClass);break}})},A=function(t,i=Et,n=null,l=null,h=null,d=null,y=null,v=null){const p=t==null?void 0:t.trim();g.states[p]===void 0?(x.info("Adding state ",p,l),g.states[p]={id:p,descriptions:[],type:i,doc:n,note:h,classes:[],styles:[],textStyles:[]}):(g.states[p].doc||(g.states[p].doc=n),g.states[p].type||(g.states[p].type=i)),l&&(x.info("Setting state description",p,l),typeof l=="string"&&bt(p,l.trim()),typeof l=="object"&&l.forEach(_=>bt(p,_.trim()))),h&&(g.states[p].note=h,g.states[p].note.text=lt.sanitizeText(g.states[p].note.text,F())),d&&(x.info("Setting state classes",p,d),(typeof d=="string"?[d]:d).forEach(_=>vt(p,_.trim()))),y&&(x.info("Setting state styles",p,y),(typeof y=="string"?[y]:y).forEach(_=>be(p,_.trim()))),v&&(x.info("Setting state styles",p,y),(typeof v=="string"?[v]:v).forEach(_=>Ee(p,_.trim())))},Gt=function(t){ct={root:Ft()},g=ct.root,N=0,w=Nt(),t||Qt()},P=function(t){return g.states[t]},de=function(){return g.states},ye=function(){x.info("Documents = ",ct)},fe=function(){return g.relations};function kt(t=""){let i=t;return t===xt&&(N++,i=`${Rt}${N}`),i}function Tt(t="",i=Et){return t===xt?Rt:i}function ge(t=""){let i=t;return t===Bt&&(N++,i=`${wt}${N}`),i}function me(t="",i=Et){return t===Bt?wt:i}function Se(t,i,n){let l=kt(t.id.trim()),h=Tt(t.id.trim(),t.type),d=kt(i.id.trim()),y=Tt(i.id.trim(),i.type);A(l,h,t.doc,t.description,t.note,t.classes,t.styles,t.textStyles),A(d,y,i.doc,i.description,i.note,i.classes,i.styles,i.textStyles),g.relations.push({id1:l,id2:d,relationTitle:lt.sanitizeText(n,F())})}const jt=function(t,i,n){if(typeof t=="object")Se(t,i,n);else{const l=kt(t.trim()),h=Tt(t),d=ge(i.trim()),y=me(i);A(l,h),A(d,y),g.relations.push({id1:l,id2:d,title:lt.sanitizeText(n,F())})}},bt=function(t,i){const n=g.states[t],l=i.startsWith(":")?i.replace(":","").trim():i;n.descriptions.push(lt.sanitizeText(l,F()))},_e=function(t){return t.substring(0,1)===":"?t.substr(2).trim():t.trim()},ke=()=>(Lt++,"divider-id-"+Lt),Yt=function(t,i=""){w[t]===void 0&&(w[t]={id:t,styles:[],textStyles:[]});const n=w[t];i!=null&&i.split(ne).forEach(l=>{const h=l.replace(/([^;]*);/,"$1").trim();if(l.match(At)){const d=h.replace(It,re).replace(At,It);n.textStyles.push(d)}n.styles.push(h)})},Te=function(){return w},vt=function(t,i){t.split(",").forEach(function(n){let l=P(n);if(l===void 0){const h=n.trim();A(h),l=P(h)}l.classes.push(i)})},be=function(t,i){const n=P(t);n!==void 0&&n.textStyles.push(i)},Ee=function(t,i){const n=P(t);n!==void 0&&n.textStyles.push(i)},xe=()=>Pt,ve=t=>{Pt=t},$e=t=>t&&t[0]===":"?t.substr(1).trim():t.trim(),Le={parseDirective:ce,getConfig:()=>F().state,addState:A,clear:Gt,getState:P,getStates:de,getRelations:fe,getClasses:Te,getDirection:xe,addRelation:jt,getDividerId:ke,setDirection:ve,cleanupLabel:_e,lineType:ae,relationType:oe,logDocuments:ye,getRootDoc:he,setRootDoc:le,getRootDocV2:ue,extract:pe,trimColon:$e,getAccTitle:Ht,setAccTitle:Xt,getAccDescription:Kt,setAccDescription:Jt,addStyleClass:Yt,setCssClass:vt,addDescription:bt,setDiagramTitle:Vt,getDiagramTitle:Wt},De=t=>`
defs #statediagram-barbEnd {
    fill: ${t.transitionColor};
    stroke: ${t.transitionColor};
  }
g.stateGroup text {
  fill: ${t.nodeBorder};
  stroke: none;
  font-size: 10px;
}
g.stateGroup text {
  fill: ${t.textColor};
  stroke: none;
  font-size: 10px;

}
g.stateGroup .state-title {
  font-weight: bolder;
  fill: ${t.stateLabelColor};
}

g.stateGroup rect {
  fill: ${t.mainBkg};
  stroke: ${t.nodeBorder};
}

g.stateGroup line {
  stroke: ${t.lineColor};
  stroke-width: 1;
}

.transition {
  stroke: ${t.transitionColor};
  stroke-width: 1;
  fill: none;
}

.stateGroup .composit {
  fill: ${t.background};
  border-bottom: 1px
}

.stateGroup .alt-composit {
  fill: #e0e0e0;
  border-bottom: 1px
}

.state-note {
  stroke: ${t.noteBorderColor};
  fill: ${t.noteBkgColor};

  text {
    fill: ${t.noteTextColor};
    stroke: none;
    font-size: 10px;
  }
}

.stateLabel .box {
  stroke: none;
  stroke-width: 0;
  fill: ${t.mainBkg};
  opacity: 0.5;
}

.edgeLabel .label rect {
  fill: ${t.labelBackgroundColor};
  opacity: 0.5;
}
.edgeLabel .label text {
  fill: ${t.transitionLabelColor||t.tertiaryTextColor};
}
.label div .edgeLabel {
  color: ${t.transitionLabelColor||t.tertiaryTextColor};
}

.stateLabel text {
  fill: ${t.stateLabelColor};
  font-size: 10px;
  font-weight: bold;
}

.node circle.state-start {
  fill: ${t.specialStateColor};
  stroke: ${t.specialStateColor};
}

.node .fork-join {
  fill: ${t.specialStateColor};
  stroke: ${t.specialStateColor};
}

.node circle.state-end {
  fill: ${t.innerEndBackground};
  stroke: ${t.background};
  stroke-width: 1.5
}
.end-state-inner {
  fill: ${t.compositeBackground||t.background};
  // stroke: ${t.background};
  stroke-width: 1.5
}

.node rect {
  fill: ${t.stateBkg||t.mainBkg};
  stroke: ${t.stateBorder||t.nodeBorder};
  stroke-width: 1px;
}
.node polygon {
  fill: ${t.mainBkg};
  stroke: ${t.stateBorder||t.nodeBorder};;
  stroke-width: 1px;
}
#statediagram-barbEnd {
  fill: ${t.lineColor};
}

.statediagram-cluster rect {
  fill: ${t.compositeTitleBackground};
  stroke: ${t.stateBorder||t.nodeBorder};
  stroke-width: 1px;
}

.cluster-label, .nodeLabel {
  color: ${t.stateLabelColor};
}

.statediagram-cluster rect.outer {
  rx: 5px;
  ry: 5px;
}
.statediagram-state .divider {
  stroke: ${t.stateBorder||t.nodeBorder};
}

.statediagram-state .title-state {
  rx: 5px;
  ry: 5px;
}
.statediagram-cluster.statediagram-cluster .inner {
  fill: ${t.compositeBackground||t.background};
}
.statediagram-cluster.statediagram-cluster-alt .inner {
  fill: ${t.altBackground?t.altBackground:"#efefef"};
}

.statediagram-cluster .inner {
  rx:0;
  ry:0;
}

.statediagram-state rect.basic {
  rx: 5px;
  ry: 5px;
}
.statediagram-state rect.divider {
  stroke-dasharray: 10,10;
  fill: ${t.altBackground?t.altBackground:"#efefef"};
}

.note-edge {
  stroke-dasharray: 5;
}

.statediagram-note rect {
  fill: ${t.noteBkgColor};
  stroke: ${t.noteBorderColor};
  stroke-width: 1px;
  rx: 0;
  ry: 0;
}
.statediagram-note rect {
  fill: ${t.noteBkgColor};
  stroke: ${t.noteBorderColor};
  stroke-width: 1px;
  rx: 0;
  ry: 0;
}

.statediagram-note text {
  fill: ${t.noteTextColor};
}

.statediagram-note .nodeLabel {
  color: ${t.noteTextColor};
}
.statediagram .edgeLabel {
  color: red; // ${t.noteTextColor};
}

#dependencyStart, #dependencyEnd {
  fill: ${t.lineColor};
  stroke: ${t.lineColor};
  stroke-width: 1;
}

.statediagramTitleText {
  text-anchor: middle;
  font-size: 18px;
  fill: ${t.textColor};
}
`,Oe=De;export{Oe as $,Le as B,Ae as R,_t as T,Ot as a,se as r,Ie as w,Et as x};
