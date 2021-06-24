/*
 * ATTENTION: An "eval-source-map" devtool has been used.
 * This devtool is neither made for production nor for readable output files.
 * It uses "eval()" calls to create a separate source file with attached SourceMaps in the browser devtools.
 * If you are trying to read the output file, select a different devtool (https://webpack.js.org/configuration/devtool/)
 * or disable the default devtool with "devtool: false".
 * If you are looking for production-ready output files, see mode: "production" (https://webpack.js.org/configuration/mode/).
 */
(function() {
var exports = {};
exports.id = "pages/index";
exports.ids = ["pages/index"];
exports.modules = {

/***/ "./pages/index.js":
/*!************************!*\
  !*** ./pages/index.js ***!
  \************************/
/***/ (function(__unused_webpack_module, __webpack_exports__, __webpack_require__) {

"use strict";
eval("__webpack_require__.r(__webpack_exports__);\n/* harmony export */ __webpack_require__.d(__webpack_exports__, {\n/* harmony export */   \"default\": function() { return /* binding */ Home; },\n/* harmony export */   \"getStaticProps\": function() { return /* binding */ getStaticProps; }\n/* harmony export */ });\n/* harmony import */ var react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! react/jsx-dev-runtime */ \"react/jsx-dev-runtime\");\n/* harmony import */ var react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_0__);\n/* harmony import */ var next_head__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! next/head */ \"next/head\");\n/* harmony import */ var next_head__WEBPACK_IMPORTED_MODULE_1___default = /*#__PURE__*/__webpack_require__.n(next_head__WEBPACK_IMPORTED_MODULE_1__);\n\nvar _jsxFileName = \"/home/humaid/Desktop/NextJS/hulu-2-yt/pages/index.js\";\n\nfunction Home({\n  articles\n}) {\n  console.log(articles);\n  return /*#__PURE__*/(0,react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_0__.jsxDEV)(\"div\", {\n    children: [/*#__PURE__*/(0,react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_0__.jsxDEV)((next_head__WEBPACK_IMPORTED_MODULE_1___default()), {\n      children: [/*#__PURE__*/(0,react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_0__.jsxDEV)(\"title\", {\n        children: \"WebDev News\"\n      }, void 0, false, {\n        fileName: _jsxFileName,\n        lineNumber: 8,\n        columnNumber: 9\n      }, this), /*#__PURE__*/(0,react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_0__.jsxDEV)(\"meta\", {\n        name: \"keywords\",\n        content: \"web development, programming\"\n      }, void 0, false, {\n        fileName: _jsxFileName,\n        lineNumber: 9,\n        columnNumber: 9\n      }, this)]\n    }, void 0, true, {\n      fileName: _jsxFileName,\n      lineNumber: 7,\n      columnNumber: 7\n    }, this), /*#__PURE__*/(0,react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_0__.jsxDEV)(\"h1\", {\n      children: \"Welcome to next\"\n    }, void 0, false, {\n      fileName: _jsxFileName,\n      lineNumber: 13,\n      columnNumber: 6\n    }, this), articles.map(article => /*#__PURE__*/(0,react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_0__.jsxDEV)(\"h3\", {\n      children: article.title\n    }, void 0, false, {\n      fileName: _jsxFileName,\n      lineNumber: 16,\n      columnNumber: 9\n    }, this))]\n  }, void 0, true, {\n    fileName: _jsxFileName,\n    lineNumber: 6,\n    columnNumber: 5\n  }, this);\n}\nconst getStaticProps = async () => {\n  try {\n    const res = await fetch(`https://jsonplaceholder.typicode.com/posts?_limit=6`);\n    const articles = await res.json();\n    return {\n      props: {\n        articles\n      }\n    };\n  } catch (e) {}\n};//# sourceURL=[module]\n//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIndlYnBhY2s6Ly9odWx1LTIteXQvLi9wYWdlcy9pbmRleC5qcz80NGQ4Il0sIm5hbWVzIjpbIkhvbWUiLCJhcnRpY2xlcyIsImNvbnNvbGUiLCJsb2ciLCJtYXAiLCJhcnRpY2xlIiwidGl0bGUiLCJnZXRTdGF0aWNQcm9wcyIsInJlcyIsImZldGNoIiwianNvbiIsInByb3BzIiwiZSJdLCJtYXBwaW5ncyI6Ijs7Ozs7Ozs7Ozs7QUFBQTtBQUVlLFNBQVNBLElBQVQsQ0FBYztBQUFDQztBQUFELENBQWQsRUFBMEI7QUFDdkNDLFNBQU8sQ0FBQ0MsR0FBUixDQUFZRixRQUFaO0FBQ0Esc0JBQ0U7QUFBQSw0QkFDRSw4REFBQyxrREFBRDtBQUFBLDhCQUNFO0FBQUE7QUFBQTtBQUFBO0FBQUE7QUFBQTtBQUFBLGNBREYsZUFFRTtBQUFNLFlBQUksRUFBQyxVQUFYO0FBQXNCLGVBQU8sRUFBQztBQUE5QjtBQUFBO0FBQUE7QUFBQTtBQUFBLGNBRkY7QUFBQTtBQUFBO0FBQUE7QUFBQTtBQUFBLFlBREYsZUFPQztBQUFBO0FBQUE7QUFBQTtBQUFBO0FBQUE7QUFBQSxZQVBELEVBU0VBLFFBQVEsQ0FBQ0csR0FBVCxDQUFhQyxPQUFPLGlCQUNsQjtBQUFBLGdCQUFLQSxPQUFPLENBQUNDO0FBQWI7QUFBQTtBQUFBO0FBQUE7QUFBQSxZQURGLENBVEY7QUFBQTtBQUFBO0FBQUE7QUFBQTtBQUFBLFVBREY7QUFnQkQ7QUFFTSxNQUFNQyxjQUFjLEdBQUcsWUFBVztBQUN2QyxNQUNBO0FBQ0UsVUFBTUMsR0FBRyxHQUFHLE1BQU1DLEtBQUssQ0FBRSxxREFBRixDQUF2QjtBQUNBLFVBQU1SLFFBQVEsR0FBRyxNQUFNTyxHQUFHLENBQUNFLElBQUosRUFBdkI7QUFFQSxXQUFPO0FBQ0xDLFdBQUssRUFBQztBQUNKVjtBQURJO0FBREQsS0FBUDtBQUtELEdBVkQsQ0FXQSxPQUFNVyxDQUFOLEVBQ0EsQ0FFQztBQUdGLENBbEJNIiwiZmlsZSI6Ii4vcGFnZXMvaW5kZXguanMuanMiLCJzb3VyY2VzQ29udGVudCI6WyJpbXBvcnQgSGVhZCBmcm9tICduZXh0L2hlYWQnXG5cbmV4cG9ydCBkZWZhdWx0IGZ1bmN0aW9uIEhvbWUoe2FydGljbGVzfSkge1xuICBjb25zb2xlLmxvZyhhcnRpY2xlcyk7XG4gIHJldHVybiAoXG4gICAgPGRpdiA+XG4gICAgICA8SGVhZD5cbiAgICAgICAgPHRpdGxlPldlYkRldiBOZXdzPC90aXRsZT5cbiAgICAgICAgPG1ldGEgbmFtZT1cImtleXdvcmRzXCIgY29udGVudD1cIndlYiBkZXZlbG9wbWVudCwgcHJvZ3JhbW1pbmdcIi8+XG4gICAgICA8L0hlYWQ+XG5cblxuICAgICA8aDE+V2VsY29tZSB0byBuZXh0PC9oMT5cbiAgICAgIHtcbiAgICAgIGFydGljbGVzLm1hcChhcnRpY2xlPT4oXG4gICAgICAgIDxoMz57YXJ0aWNsZS50aXRsZX08L2gzPlxuICAgICAgKSlcbiAgICAgIH1cbiAgICA8L2Rpdj5cbiAgKVxufVxuXG5leHBvcnQgY29uc3QgZ2V0U3RhdGljUHJvcHMgPSBhc3luYyAoKSA9PntcbiAgdHJ5XG4gIHtcbiAgICBjb25zdCByZXMgPSBhd2FpdCBmZXRjaChgaHR0cHM6Ly9qc29ucGxhY2Vob2xkZXIudHlwaWNvZGUuY29tL3Bvc3RzP19saW1pdD02YCk7XG4gICAgY29uc3QgYXJ0aWNsZXMgPSBhd2FpdCByZXMuanNvbigpO1xuICBcbiAgICByZXR1cm4ge1xuICAgICAgcHJvcHM6e1xuICAgICAgICBhcnRpY2xlc1xuICAgICAgfVxuICAgIH1cbiAgfVxuICBjYXRjaChlKVxuICB7XG4gICAgXG4gIH1cbiBcblxufSJdLCJzb3VyY2VSb290IjoiIn0=\n//# sourceURL=webpack-internal:///./pages/index.js\n");

/***/ }),

/***/ "next/head":
/*!****************************!*\
  !*** external "next/head" ***!
  \****************************/
/***/ (function(module) {

"use strict";
module.exports = require("next/head");;

/***/ }),

/***/ "react/jsx-dev-runtime":
/*!****************************************!*\
  !*** external "react/jsx-dev-runtime" ***!
  \****************************************/
/***/ (function(module) {

"use strict";
module.exports = require("react/jsx-dev-runtime");;

/***/ })

};
;

// load runtime
var __webpack_require__ = require("../webpack-runtime.js");
__webpack_require__.C(exports);
var __webpack_exec__ = function(moduleId) { return __webpack_require__(__webpack_require__.s = moduleId); }
var __webpack_exports__ = (__webpack_exec__("./pages/index.js"));
module.exports = __webpack_exports__;

})();