(ns ^:figwheel-hooks re-frame-forms.core
  (:require
   [goog.dom :as gdom]
   [reagent.core :as r]
   [reagent.dom :as rdom]
   [re-frame.core :as rf]
   [re-frame.db :as db]))

(def fields (r/atom {:name ""
                     :text ""}))

(rf/reg-event-db
 :initialize
 (fn [_ _]
   @fields))

(rf/dispatch-sync [:initialize])

(rf/reg-event-db
 :name-event
 (fn [fields [_ new-name]]
   (assoc fields :name new-name)))

(rf/reg-event-db
 :text-event
 (fn [fields [_ new-text]]
   (assoc fields :text new-text)))

(rf/reg-event-db
 :update-form
 (fn [fields [_ new-name new-text]]
   (assoc fields :name new-name :text new-text)))

(defn form-fields []
  (fn []
    [:div.div
     [:div.fields
      [:label.label {:for :name} "Name"]
      [:input.input {:type :text
                     :name :name
                     :value @(rf/subscribe [:name])
                     :on-change #(rf/dispatch [:name-event (-> % .-target .-value)])}]]
     [:br]
     [:div.fields
      [:label.label {:for :text} "Text"]
      [:textarea.textarea
       {:value @(rf/subscribe [:text])
        :on-change #(rf/dispatch [:text-event (-> % .-target .-value)])}]]
     [:br]
     (comment
     [:div.fields
      [:input.button.is-primary {:type :submit
                                 :value "Save"
                                 :on-click #(rf/dispatch [:update-form name text])}]])]))

(rf/reg-sub
 :name
 (fn [fields _]
   (:name fields)))

(rf/reg-sub
 :text
 (fn [fields _]
   (:text fields)))

(defn mount-app-element []
  (rdom/render
   [form-fields]
   (gdom/getElement "app")))

;; conditionally start your application based on the presence of an "app" element
;; this is particularly helpful for testing this ns without launching the app
(mount-app-element)

;; specify reload hook with ^:after-load metadata
(defn ^:after-load on-reload []
  (mount-app-element)
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
  )
